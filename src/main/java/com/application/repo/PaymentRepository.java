package com.application.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.application.PaymentDetails;
import com.application.SignUp;
@Repository
public interface PaymentRepository extends JpaRepository<PaymentDetails, Long>{
	List<PaymentDetails> findBySignUp(SignUp signUp);
	

}
