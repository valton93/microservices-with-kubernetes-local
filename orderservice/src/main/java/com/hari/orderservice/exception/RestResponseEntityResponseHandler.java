package com.hari.orderservice.exception;

import org.springframework.http.HttpStatusCode;
import org.springframework.http.ProblemDetail;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class RestResponseEntityResponseHandler {
	
	@ExceptionHandler(OrderServiceCustomException.class)
	ProblemDetail handleOrderServiceException(OrderServiceCustomException exception) {
		ProblemDetail problemDetail=ProblemDetail.forStatusAndDetail(HttpStatusCode.valueOf( exception.getCustomHttpStatus().value()), exception.getMessage());
		return problemDetail; 
	}
}
