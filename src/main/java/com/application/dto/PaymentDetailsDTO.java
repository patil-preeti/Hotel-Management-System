package com.application.dto;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

import com.application.PaymentDetails;
import com.application.SignUp;

public class PaymentDetailsDTO {
    private SignUp signUp;
    private List<PaymentDetails> paymentDetailsList; 
    private List<PaymentDetails> upcomingTrips;
    private List<PaymentDetails> pastTrips;


    // Default Constructor
    public PaymentDetailsDTO() {}

 // Constructor for adding payment details 
    public PaymentDetailsDTO(SignUp signUp, List<PaymentDetails> paymentDetailsList) {
        this.signUp = signUp;
        this.paymentDetailsList = paymentDetailsList;
        this.upcomingTrips = null;
        this.pastTrips = null;
    }
    
 // Constructor for getting details 
    public PaymentDetailsDTO(SignUp signUp, List<PaymentDetails> upcomingTrips, List<PaymentDetails> pastTrips) {
        this.signUp = signUp;
        this.paymentDetailsList = null;
        this.upcomingTrips = upcomingTrips;
        this.pastTrips = pastTrips;
    }

    // Getters and Setters
    public SignUp getSignUp() {
        return signUp;
    }

    public void setSignUp(SignUp signUp) {
        this.signUp = signUp;
    }

    public List<PaymentDetails> getPaymentDetailsList() {
        return paymentDetailsList;
    }

    public void setPaymentDetailsList(List<PaymentDetails> paymentDetailsList) {
        this.paymentDetailsList = paymentDetailsList;
    }

    public List<PaymentDetails> getUpcomingTrips() {
        return upcomingTrips;
    }

    public void setUpcomingTrips(List<PaymentDetails> upcomingTrips) {
        this.upcomingTrips = upcomingTrips;
    }

    public List<PaymentDetails> getPastTrips() {
        return pastTrips;
    }

    public void setPastTrips(List<PaymentDetails> pastTrips) {
        this.pastTrips = pastTrips;
    }
}
