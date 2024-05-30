
package com.hari.product.exception;
import com.hari.product.exception.utility.CustomHttpStatus;

import lombok.Data;

@Data
public class ProductServiceCustomException extends RuntimeException {
	
	private static final long serialVersionUID = 1L;
	private CustomHttpStatus customHttpStatus;
	private String message;
	
	public ProductServiceCustomException(CustomHttpStatus customHttpStatus,String message) {
		this.customHttpStatus=customHttpStatus;
		this.message=message;
	}
	

}
