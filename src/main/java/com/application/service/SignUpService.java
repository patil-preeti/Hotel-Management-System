package com.application.service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.application.PaymentDetails;
import com.application.SignUp;
import com.application.dto.PaymentDetailsDTO;
import com.application.dto.SignUpDTO;
import com.application.exception.ResourceNotFoundException;
import com.application.repo.SignUpRepository;


@Service
public class SignUpService {

	@Autowired
	private SignUpRepository signUpRepository;

	

	public SignUp saveModel(SignUpDTO dto) {
	    SignUp signUp = new SignUp();
	    signUp.setFullName(dto.getFullName());
	    signUp.setDob(dto.getDob());
	    signUp.setEmail(dto.getEmail());
	    signUp.setMobileNo(dto.getMobileNo());
	    signUp.setAddress(dto.getAddress());
	    signUp.setEmergencyContact(dto.getEmergencyContact());
	    signUp.setGovtId(dto.getGovtId());;

//	    // Handling the PaymentDetails
//	    if (dto.getPaymentDetails() != null && !dto.getPaymentDetails().isEmpty()) {
//	        List<PaymentDetails> paymentDetailsList = new ArrayList<>();
//	        for (PaymentDetailsDTO paymentDTO : dto.getPaymentDetails()) {
//	            PaymentDetails paymentDetails = new PaymentDetails();
//	            paymentDetails.setHotelName(paymentDTO.getHotelName());
//	            paymentDetails.setAddress(paymentDTO.getAddress());
//	            paymentDetails.setCheckIn(paymentDTO.getCheckIn());
//	            paymentDetails.setCheckOut(paymentDTO.getCheckOut());
//	            paymentDetails.setPrice(paymentDTO.getPrice());
//	            paymentDetails.setPayment(paymentDTO.getPayment());
//	            paymentDetails.setHotelImg(paymentDTO.getHotelImg());
//
//	            // Associate payment details with the signUp
//	            paymentDetails.setSignUp(signUp);  // Ensure paymentDetails are associated with SignUp
//	            paymentDetailsList.add(paymentDetails);
//	        }
//	        signUp.setPaymentDetails(paymentDetailsList);  // Set the paymentDetails for the SignUp
//	    }

	    // Save the SignUp with the associated PaymentDetails
	    SignUp savedSignUp = signUpRepository.save(signUp);
	    return savedSignUp;
	}


	    // Update an existing entry
	    public SignUp updateModel(Long id, SignUpDTO dto) {
	        SignUp existingSignUp = signUpRepository.findById(id)
	                .orElseThrow(() -> new ResourceNotFoundException("Entry not found with id: " + id));
	        existingSignUp.setFullName(dto.getFullName());
	        existingSignUp.setDob(dto.getDob());
	        
	        existingSignUp.setEmail(dto.getEmail());
	        existingSignUp.setMobileNo(dto.getMobileNo());
	        existingSignUp.setAddress(dto.getAddress());
	        existingSignUp.setEmergencyContact(dto.getEmergencyContact());
	        existingSignUp.setGovtId(dto.getGovtId());
	        return signUpRepository.save(existingSignUp);
	    }

	    // Delete an entry by ID
	    public void deleteModel(Long id) {
	        if (!signUpRepository.existsById(id)) {
	            throw new ResourceNotFoundException("Entry not found with id: " + id);
	        }
	        signUpRepository.deleteById(id);
	    }

	    public List<SignUp> getAllData() {
	        return signUpRepository.findAll();
	    }
	    
	    public ResponseEntity<?> saveSignUp(SignUpDTO dto) {
	        try {
	            // Check if the mobileNo or emailId already exists in the database
	        	String email = dto.getEmail();
	        	String mobileNo = dto.getMobileNo();
	            Optional<SignUp> existingData = signUpRepository.findByMobileNo(mobileNo);
	            Optional<SignUp> existingData1 = signUpRepository.findByEmail(email);

	            if (existingData.isPresent()) {
	                // Return conflict status if data already exists
	                throw new DuplicateException("Data already exists for the provided mobile number"+mobileNo);
	            }
	            else if (existingData1.isPresent()) {
	                // Return conflict status if data already exists
	                throw new DuplicateException("Data already exists for the provided email"+email);
	            }
	            else  {
	            // Generate a unique customerId
	            LocalDateTime now = LocalDateTime.now();
	            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("mmss");
	            String customerId = now.format(formatter);

	            // Create a new SignUp object and populate it
	            SignUp signUp = new SignUp();
	            signUp.setFullName(dto.getFullName());
	            signUp.setDob(dto.getDob());
	            signUp.setEmail(dto.getEmail());
	            signUp.setMobileNo(dto.getMobileNo());
	           

	            // Save the data to the repository
	            signUpRepository.save(signUp);

	            return ResponseEntity.status(HttpStatus.CREATED)
	                                 .body("Data stored successfully");
	            }
	        } catch (DuplicateException e) {
	            return ResponseEntity.status(HttpStatus.CONFLICT)
	                                 .body(e.getMessage());
	        } catch (Exception e) {
	            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
	                                 .body("An unexpected error occurred: " + e.getMessage());
	        }
	    }

	    // Custom exception class for data conflict
	    public class DuplicateException extends RuntimeException {
	        public DuplicateException(String message) {
	            super(message);
	        }
	    }
	    
	  
	    public <SignUpModel> ResponseEntity<?> findByMobileNoOrEMail(String mobileNo) {
	        Optional<SignUp> data = signUpRepository.findByMobileNo(mobileNo);
	        

	        if (data.isEmpty()) {
	            return ResponseEntity.status(HttpStatus.NOT_FOUND)
	                                 .body("No data found for mobile number : " + mobileNo );
	        }

	        return ResponseEntity.status(HttpStatus.OK).body("Found");
	    }

		public ResponseEntity<?> findByMobileNo123(String mobileNo) {
			
			 Optional<SignUp> data = signUpRepository.findByMobileNo(mobileNo);
			 

		        if (data.isEmpty()) {
		            return ResponseEntity.status(HttpStatus.NOT_FOUND)
		                                 .body("No data found for mobile number : " + mobileNo );
		        }
		        else
		        {
		        	SignUp details = data.get();
		        
		        return ResponseEntity.status(HttpStatus.OK).body(details);
		        
		        }
		}

		
		
}