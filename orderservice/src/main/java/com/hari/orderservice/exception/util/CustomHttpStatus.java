package com.hari.orderservice.exception.util;

import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatus.Series;

public enum CustomHttpStatus {
	
    /**
     * {@code 404 Order Not Found}.
     */
	ORDER_NOT_FOUND(HttpStatus.NOT_FOUND.value(), "ORDER NOT FOUND"),
	PAYMENT_SERVICE_NOT_AVAILABLE(HttpStatus.SERVICE_UNAVAILABLE.value(),"PAYMENT SERVICE NOT AVAILABLE"),
	PRODUCT_SERVICE_NOT_AVAILABLE(HttpStatus.SERVICE_UNAVAILABLE.value(),"PRODUCT SERVICE NOT AVAILABLE");
	
	private final int value;
    private final String reasonPhrase;

    CustomHttpStatus(int value,  String reasonPhrase) {
        this.value = value;
        this.reasonPhrase = reasonPhrase;
    }

    public int value() {
        return this.value;
    }


    public String getReasonPhrase() {
        return this.reasonPhrase;
    }


}
