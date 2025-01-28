package com.application.service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

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
 //Add data
    public PaymentDetailsDTO addPaymentDetails(String mobileNo, PaymentDetailsDTO paymentDetailsDTO) {
        SignUp signUp = signUpRepository.findByMobileNo(mobileNo)
                .orElseThrow(() -> new ResourceNotFoundException("User with mobile number " + mobileNo + " not found"));

        List<PaymentDetails> paymentDetailsList = new ArrayList<>();
        for (PaymentDetails paymentDetails : paymentDetailsDTO.getPaymentDetailsList()) {
            paymentDetails.setSignUp(signUp);
            paymentDetailsList.add(paymentDetails);
        }

        paymentRepository.saveAll(paymentDetailsList);

        return new PaymentDetailsDTO(signUp, paymentDetailsList);
    }




    // get data
    public PaymentDetailsDTO getPaymentDetailsByMobile(String mobileNo) {
        SignUp signUp = signUpRepository.findByMobileNo(mobileNo)
                .orElseThrow(() -> new ResourceNotFoundException("User with mobile number " + mobileNo + " not found"));

        List<PaymentDetails> allTrips = paymentRepository.findBySignUp(signUp);

        List<PaymentDetails> upcomingTrips = allTrips.stream()
                .filter(trip -> trip.getCheckIn().isAfter(LocalDate.now()))
                .collect(Collectors.toList());

        List<PaymentDetails> pastTrips = allTrips.stream()
                .filter(trip -> trip.getCheckOut().isBefore(LocalDate.now()))
                .collect(Collectors.toList());

        return new PaymentDetailsDTO(signUp, upcomingTrips, pastTrips);
    }

    //  Update payment details
    public PaymentDetails updatePaymentDetails(Long id, PaymentDetails paymentDetails) {
        PaymentDetails existingPaymentDetails = paymentRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Payment details not found for id " + id));

        existingPaymentDetails.setHotelName(paymentDetails.getHotelName());
        existingPaymentDetails.setAddress(paymentDetails.getAddress());
        existingPaymentDetails.setCheckIn(paymentDetails.getCheckIn());
        existingPaymentDetails.setCheckOut(paymentDetails.getCheckOut());
        existingPaymentDetails.setPrice(paymentDetails.getPrice());
        existingPaymentDetails.setPayment(paymentDetails.getPayment());
        existingPaymentDetails.setHotelImg(paymentDetails.getHotelImg());

        return paymentRepository.save(existingPaymentDetails);
    }

    //  Delete payment details
    public void deletePaymentDetails(Long id) {
        PaymentDetails paymentDetails = paymentRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Payment details not found for id " + id));
        paymentRepository.delete(paymentDetails);
    }

    //  Get payment details by ID
    public PaymentDetails getPaymentDetailsById(Long id) {
        return paymentRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Payment details not found for id " + id));
    }
}
