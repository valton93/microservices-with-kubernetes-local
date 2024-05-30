package com.example.paymentservice.exception.util;

public enum CustomHttpStatus {
	PAYMENT_NOt_FOUND(410,"PAYMENT Not FOUND");
	
	private final int value;
	private final String reasonPhrase; 
	
	CustomHttpStatus(int value,String reasonPhrase ){
		this.value=value;
		this.reasonPhrase=reasonPhrase;
	}
	
	public int value() {
		return this.value;
	}
	public String reasonPhrase() {
		return this.reasonPhrase;
	}

}
