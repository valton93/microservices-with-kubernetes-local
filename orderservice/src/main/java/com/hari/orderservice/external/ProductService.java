package com.hari.orderservice.external;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.hari.orderservice.exception.OrderServiceCustomException;
import com.hari.orderservice.exception.util.CustomHttpStatus;

import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;


@CircuitBreaker(name = "external",fallbackMethod = "fallback")
@FeignClient(name="PRODUCT-SERVICE/v1/product")
public interface ProductService {
	@PutMapping("/reduceQuantity/{id}")
	ResponseEntity<Long> reduceQuantity(@PathVariable("id")long productId, @RequestParam long quantity);
	
	default ResponseEntity<Long> fallback(Exception e) {
		throw new OrderServiceCustomException(CustomHttpStatus.PRODUCT_SERVICE_NOT_AVAILABLE, CustomHttpStatus.PRODUCT_SERVICE_NOT_AVAILABLE.getReasonPhrase()) ;
	}
}
