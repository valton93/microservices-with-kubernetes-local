package com.example.paymentservice.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ProblemDetail;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class RestResponseCustomExceptionHandler {
	
	@ExceptionHandler(PaymentCustomException.class)
	ProblemDetail handlePaymentServiceException(PaymentCustomException exception) {
		return ProblemDetail.forStatusAndDetail(HttpStatus.valueOf(exception.getCustomHttpStatus().value()), exception.getMessage());
	}

}
