package com.application.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.application.PaymentDetails;
import com.application.SignUp;
import com.application.dto.PaymentDetailsDTO;
import com.application.exception.ResourceNotFoundException;
import com.application.repo.PaymentRepository;
import com.application.repo.SignUpRepository;

@Service
public class PaymentService {

    @Autowired
    private PaymentRepository paymentRepository;
    @Autowired
    private SignUpRepository signUpRepository;

    // Add payment details
//    public PaymentDetails addPaymentDetails(String mobileNo, PaymentDetails paymentDetails) {
//        // Logic to fetch SignUp data using mobileNo and associate with paymentDetails
//        // set the SignUp entity (similar to existing code)
//        return paymentRepository.save(paymentDetails);
//    }
    
    public PaymentDetailsDTO addPaymentDetails(String mobileNo, PaymentDetailsDTO paymentDetailsDTO) {
        // Fetch the SignUp entity using the mobile number
        SignUp signUp = signUpRepository.findByMobileNo(mobileNo)
                .orElseThrow(() -> new ResourceNotFoundException("User with mobile number " + mobileNo + " not found"));

        // Convert DTO to Entity and associate with SignUp
        List<PaymentDetails> paymentDetailsList = new ArrayList<>();
        for (PaymentDetails paymentDetails : paymentDetailsDTO.getPaymentDetailsList()) {
            paymentDetails.setSignUp(signUp); // Associate payment details with the user
            paymentDetailsList.add(paymentDetails);
        }

        // Save all payment details
        paymentRepository.saveAll(paymentDetailsList);

        // Return the response DTO
        return new PaymentDetailsDTO(signUp, paymentDetailsList);
    }


    // Get payment details by ID
    public PaymentDetails getPaymentDetailsById(Long id) {
        return paymentRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Payment details not found for id " + id));
    }
// // Get payment details by mobile number
//    public PaymentDetails getPaymentDetailsByMobile(String mobileNo) {
//        // Fetch SignUp entity using the mobile number
//        SignUp signUp = signUpRepository.findByMobileNo(mobileNo)
//                .orElseThrow(() -> new ResourceNotFoundException("User with mobile number " + mobileNo + " not found"));
//
//        // Fetch payment details associated with the SignUp entity
//        return paymentRepository.findBySignUp(signUp)
//                .orElseThrow(() -> new ResourceNotFoundException("No payment details found for user with mobile number " + mobileNo));
//    }
    
    public PaymentDetailsDTO getPaymentDetailsByMobile(String mobileNo) {
        // Fetch SignUp entity using the mobile number
        SignUp signUp = signUpRepository.findByMobileNo(mobileNo)
                .orElseThrow(() -> new ResourceNotFoundException("User with mobile number " + mobileNo + " not found"));

        // Fetch payment details associated with the SignUp entity
        List<PaymentDetails> paymentDetailsList = paymentRepository.findBySignUp(signUp);
        if (paymentDetailsList.isEmpty()) {
            throw new ResourceNotFoundException("No payment details found for user with mobile number " + mobileNo);
        }

        // Return both SignUp and PaymentDetails list
        return new PaymentDetailsDTO(signUp, paymentDetailsList);
    }


    // Update payment details by ID
    public PaymentDetails updatePaymentDetails(Long id, PaymentDetails paymentDetails) {
        PaymentDetails existingPaymentDetails = paymentRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Payment details not found for id " + id));

        // Update existing payment details with the new ones
        existingPaymentDetails.setHotelName(paymentDetails.getHotelName());
        existingPaymentDetails.setAddress(paymentDetails.getAddress());
        existingPaymentDetails.setCheckIn(paymentDetails.getCheckIn());
        existingPaymentDetails.setCheckOut(paymentDetails.getCheckOut());
        existingPaymentDetails.setPrice(paymentDetails.getPrice());
        existingPaymentDetails.setPayment(paymentDetails.getPayment());
        existingPaymentDetails.setHotelImg(paymentDetails.getHotelImg());

        return paymentRepository.save(existingPaymentDetails);
    }

    // Delete payment details by ID
    public void deletePaymentDetails(Long id) {
        PaymentDetails paymentDetails = paymentRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Payment details not found for id " + id));
        paymentRepository.delete(paymentDetails);
    }
}
