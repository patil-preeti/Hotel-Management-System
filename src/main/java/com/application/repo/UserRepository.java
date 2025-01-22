package com.application.repo;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.application.SignUp;

public interface UserRepository extends JpaRepository<SignUp, Long>{

	  Optional<SignUp> findByEmail(String email);
	    boolean existsByEmail(String email);
	    boolean existsByMobileNo(String mobileNo);
	
}
