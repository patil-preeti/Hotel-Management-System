package com.application.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.application.SignUp;
import com.application.dto.SignUpDto;
import com.application.exception.DuplicateException;
import com.application.exception.ResourceNotFoundException;
import com.application.repo.SignUpRepository;

@Service
public class SignUpService {

	@Autowired
	private SignUpRepository signUpRepository;

	

	    // Save a new entry
	    public SignUp saveModel(SignUpDto dto) {
	        if (signUpRepository.existsByEmail(dto.getEmail())) {
	            throw new DuplicateException("Email already exists: " + dto.getEmail());
	        }
	        SignUp signUp = new SignUp();
	        signUp.setFullName(dto.getFullName());
	        signUp.setdOB(dto.getdOB());
	        signUp.setEmail(dto.getEmail());
	        signUp.setMobileNo(dto.getMobileNo());
	        return signUpRepository.save(signUp);
	    }

	    // Update an existing entry
	    public SignUp updateModel(Long id, SignUpDto dto) {
	        SignUp existingSignUp = signUpRepository.findById(id)
	                .orElseThrow(() -> new ResourceNotFoundException("Entry not found with id: " + id));
	        existingSignUp.setFullName(dto.getFullName());
	        existingSignUp.setdOB(dto.getdOB());
	        
	        existingSignUp.setEmail(dto.getEmail());
	        existingSignUp.setMobileNo(dto.getMobileNo());
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



}
