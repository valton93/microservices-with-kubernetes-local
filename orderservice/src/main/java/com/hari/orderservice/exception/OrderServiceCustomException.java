package com.hari.orderservice.exception;

import com.hari.orderservice.exception.util.CustomHttpStatus;

import lombok.Data;


@Data
public class OrderServiceCustomException extends RuntimeException{
	
	private static final long serialVersionUID = 1L;
	private CustomHttpStatus customHttpStatus;
	
	public OrderServiceCustomException(CustomHttpStatus customHttpStatus,String message) {
		super(message);
		this.customHttpStatus=customHttpStatus;
	}

}
