package com.application.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.application.SignUp;

@Repository
public interface SignUpRepository extends JpaRepository<SignUp, Long>{
	
	    boolean existsByEmail(String email);
	

}
