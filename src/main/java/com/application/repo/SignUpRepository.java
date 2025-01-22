package com.application.repo;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.application.SignUp;

@Repository
public interface SignUpRepository extends JpaRepository<SignUp, Long> {
	Optional<SignUp> findByEmail(String email);
	Optional<SignUp> findByMobileNo(String mobileNo);
    boolean existsByEmail(String email);
    boolean existsByMobileNo(String mobileNo);

}
