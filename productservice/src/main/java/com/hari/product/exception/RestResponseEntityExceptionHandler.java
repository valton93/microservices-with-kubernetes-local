package com.hari.product.exception;

import org.springframework.http.HttpStatusCode;
import org.springframework.http.ProblemDetail;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;


@RestControllerAdvice
public class RestResponseEntityExceptionHandler extends ResponseEntityExceptionHandler {
	
	@SuppressWarnings("static-access")
	@ExceptionHandler(ProductServiceCustomException.class)
	ProblemDetail handleProductServiceException(ProductServiceCustomException exception){
		ProblemDetail problemDetail = ProblemDetail
				.forStatusAndDetail(HttpStatusCode.valueOf(exception.getCustomHttpStatus().value()),exception.getMessage());
//		problemDetail.setProperty("cause",exception.getMessage());
		return problemDetail;
	}
	
	

}
