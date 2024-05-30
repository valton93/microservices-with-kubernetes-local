package com.hari.orderservice.external;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.hari.orderservice.exception.OrderServiceCustomException;
import com.hari.orderservice.exception.util.CustomHttpStatus;
import com.hari.orderservice.external.request.PaymentRequest;

import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;


@CircuitBreaker(name = "external" ,fallbackMethod = "fallback")
@FeignClient(name="PAYMENT-SERVICE/v1/payment")
public interface PaymentService {
	@PostMapping("/doPayment")
	public ResponseEntity<Long> doPayment(@RequestBody PaymentRequest paymentRequest );
	
	default ResponseEntity<Long> fallback(Exception e) {
		throw new OrderServiceCustomException(CustomHttpStatus.PAYMENT_SERVICE_NOT_AVAILABLE, CustomHttpStatus.PAYMENT_SERVICE_NOT_AVAILABLE.getReasonPhrase());
		
	}
}
