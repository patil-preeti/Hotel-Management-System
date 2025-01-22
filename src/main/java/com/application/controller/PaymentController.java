package com.application.controller;

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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.application.PaymentDetails;
import com.application.dto.PaymentDetailsDTO;
import com.application.service.PaymentService;

@RestController
@RequestMapping("/payment")
public class PaymentController {

    @Autowired
    private PaymentService paymentService;

    // POST method to add payment details
//    @PostMapping("/add")
//    public ResponseEntity<PaymentDetails> addPaymentDetails(@RequestParam String mobileNo, @RequestBody PaymentDetails paymentDetails) {
//        PaymentDetails savedPaymentDetails = paymentService.addPaymentDetails(mobileNo, paymentDetails);
//        return ResponseEntity.ok(savedPaymentDetails);
//    }
    @PostMapping("/add")
    public ResponseEntity<PaymentDetailsDTO> addPaymentDetails(
            @RequestParam String mobileNo, 
            @RequestBody PaymentDetailsDTO paymentDetailsDTO) {
        
        PaymentDetailsDTO responseDTO = paymentService.addPaymentDetails(mobileNo, paymentDetailsDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(responseDTO);
    }


    // GET method to retrieve payment details by ID
    @GetMapping("/{id}")
    public ResponseEntity<PaymentDetails> getPaymentDetailsById(@PathVariable Long id) {
        PaymentDetails paymentDetails = paymentService.getPaymentDetailsById(id);
        return ResponseEntity.ok(paymentDetails);
    }

    @GetMapping("/by-mobile/{mobileNo}")
    public ResponseEntity<PaymentDetailsDTO> getPaymentDetailsByMobile(@PathVariable String mobileNo) {
        PaymentDetailsDTO responseDTO = paymentService.getPaymentDetailsByMobile(mobileNo);
        return ResponseEntity.ok(responseDTO);
    }

    // PUT method to update payment details by ID
    @PutMapping("/update/{id}")
    public ResponseEntity<PaymentDetails> updatePaymentDetails(@PathVariable Long id, @RequestBody PaymentDetails paymentDetails) {
        PaymentDetails updatedPaymentDetails = paymentService.updatePaymentDetails(id, paymentDetails);
        return ResponseEntity.ok(updatedPaymentDetails);
    }

    // DELETE method to delete payment details by ID
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deletePaymentDetails(@PathVariable Long id) {
        paymentService.deletePaymentDetails(id);
        return ResponseEntity.ok("Payment details deleted successfully");
    }
}
