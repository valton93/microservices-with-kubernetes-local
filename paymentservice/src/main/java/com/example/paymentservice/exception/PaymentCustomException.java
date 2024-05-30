package com.example.paymentservice.exception;

import com.example.paymentservice.exception.util.CustomHttpStatus;

import lombok.Data;

@Data
public class PaymentCustomException extends RuntimeException {
	
	private static final long serialVersionUID = 1L;
	private CustomHttpStatus customHttpStatus;
	private String message;
	
	public PaymentCustomException(CustomHttpStatus customHttpStatus,String message){
		this.customHttpStatus=customHttpStatus;
		this.message=message;
	}

}
