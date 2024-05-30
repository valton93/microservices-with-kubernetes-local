package com.hari.product.exception.utility;

import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatus.Series;

import lombok.Data;

public enum CustomHttpStatus {

    // Your custom HTTP status codes here

    /**
     * {@code 404 Product Not Found}.
     */
    PRODUCT_NOT_FOUND(HttpStatus.NOT_FOUND.value(), "PRODUCT NOT FOUND"),
	
	PRODUCT_INSUFFICIENT_QUANTITY(HttpStatus.CONFLICT.value(),"Product has Insufficient Quantity");

    private final int value;
    private final String reasonPhrase;

    CustomHttpStatus(int value, String reasonPhrase) {
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