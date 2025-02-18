package com.application.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.application.SignUp;
import com.application.dto.SignUpDTO;
import com.application.service.SignUpService;

@RestController
@RequestMapping("/auth")
public class SignUpController {

	
	 @Autowired
	    private SignUpService signUpService;

	    // Create a new entry (POST API)
	    @PostMapping("/post")
	    public ResponseEntity<SignUp> createSignUp(@RequestBody SignUpDTO dto) {
	        SignUp savedSignUp = signUpService.saveModel(dto);
	       return ResponseEntity.status(HttpStatus.CREATED).body(savedSignUp);
	   }

	    // Retrieve all entries (GET API)
	    @GetMapping("/get")
	    public ResponseEntity<List<SignUp>> getAllSignUps() {
	        List<SignUp> signUpList = signUpService.getAllData();
	        return ResponseEntity.ok(signUpList);
	    }
	    @GetMapping("/get/{mobileNo}")
		public ResponseEntity<?> findBymobileNoOrEMail(@PathVariable String mobileNo )
		{
			return signUpService.findByMobileNoOrEMail(mobileNo);
		}
	    @GetMapping("/getByMobileNumber/{mobileNo}")
	    public ResponseEntity<?> findByMobileNo(@PathVariable String mobileNo) {
	      return signUpService.findByMobileNo123(mobileNo);
	    }
	   
	    


//	    @PostMapping("/posting")
//	    public ResponseEntity<?> post(@RequestBody SignUpDTO dto ) 
//	    {
//	    	return signUpService.saveSignUp(dto);
//	    }

	    // Update an existing entry by ID (PUT API)
	    @PutMapping("/update/{id}")
	    public ResponseEntity<SignUp> updateSignUp(
	            @PathVariable Long id,
	            @RequestBody SignUpDTO dto) {
	        SignUp updatedSignUp = signUpService.updateModel(id, dto);
	        return ResponseEntity.ok(updatedSignUp);
	    }

	    // Delete an entry by ID (DELETE API)
	    @DeleteMapping("/delete/{id}")
	    public ResponseEntity<String> deleteSignUp(@PathVariable Long id) {
	        signUpService.deleteModel(id);
	        return ResponseEntity.ok("Entry deleted successfully.");
	    }
	    
	    
	
}