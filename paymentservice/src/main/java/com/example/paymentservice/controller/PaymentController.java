package com.example.paymentservice.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.paymentservice.model.PaymentRequest;
import com.example.paymentservice.model.PaymentResponse;
import com.example.paymentservice.service.PaymentService;

@RestController()
@RequestMapping("/v1/payment")
public class PaymentController {
	
	@Autowired
	private PaymentService paymentService;
	
	@PostMapping("/doPayment")
	public ResponseEntity<Long> doPayment(@RequestBody PaymentRequest paymentRequest ){
		
		return new ResponseEntity<>(paymentService.doPayment(paymentRequest),HttpStatus.OK);
	}
	
	@GetMapping("/getPaymentDetailsByOrderId")
	public ResponseEntity<PaymentResponse>getPaymentDetailsByOrderId(@RequestParam(name="orderId")long orderId){
		PaymentResponse paymentResponse=paymentService.getPaymentDetailsByOrderId(orderId);
		return new ResponseEntity<>(paymentResponse,HttpStatus.OK);
		
	}
	

}
