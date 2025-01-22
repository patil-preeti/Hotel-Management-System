package com.application.dto;

import java.util.List;

import com.application.PaymentDetails;
import com.application.SignUp;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
public class PaymentDetailsDTO {


	    private SignUp signUp;
	    private List<PaymentDetails> paymentDetailsList;

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

		public PaymentDetailsDTO(SignUp signUp, List<PaymentDetails> paymentDetailsList) {
	        this.signUp = signUp;
	        this.paymentDetailsList = paymentDetailsList;
	    }

	

}
