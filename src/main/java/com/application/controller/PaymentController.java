package com.application.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.application.PaymentDetails;
import com.application.dto.PaymentDetailsDTO;
import com.application.service.PaymentService;

@RestController
@RequestMapping("/payment")
public class PaymentController {

    @Autowired
    private PaymentService paymentService;

    @PostMapping("/add")
    public ResponseEntity<PaymentDetailsDTO> addPaymentDetails(
            @RequestParam String mobileNo, 
            @RequestBody PaymentDetails paymentDetails) {

        PaymentDetailsDTO responseDTO = paymentService.addPaymentDetails(mobileNo, paymentDetails);
        return ResponseEntity.status(HttpStatus.CREATED).body(responseDTO);
    }

    
    @GetMapping("/{id}")
    public ResponseEntity<PaymentDetails> getPaymentDetailsById(@PathVariable Long id) {
        return ResponseEntity.ok(paymentService.getPaymentDetailsById(id));
    }

    @GetMapping("/by-mobile/{mobileNo}")
    public ResponseEntity<PaymentDetailsDTO> getPaymentDetailsByMobile(@PathVariable String mobileNo) {
        return ResponseEntity.ok(paymentService.getPaymentDetailsByMobile(mobileNo));
    }
    
    /**
     * Update existing payment details.
     */
    @PutMapping("/update/{id}")
    public ResponseEntity<PaymentDetails> updatePaymentDetails(
            @PathVariable Long id, 
            @RequestBody PaymentDetails updatedDetails) {
        return ResponseEntity.ok(paymentService.updatePaymentDetails(id, updatedDetails));
    }

    /**
     * Delete payment details by ID.
     */
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deletePaymentDetails(@PathVariable Long id) {
        paymentService.deletePaymentDetails(id);
        return ResponseEntity.ok("Payment details with ID " + id + " deleted successfully.");
    }
}
