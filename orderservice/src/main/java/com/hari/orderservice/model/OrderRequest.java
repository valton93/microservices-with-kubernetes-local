package com.hari.orderservice.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@AllArgsConstructor
@Builder
public class OrderRequest {

	private String orderName;
	
	private long productId;
	
	private Double totalAmount;
	private long quantity ;
	
	private PaymentMode paymentMode;
	
	
	
}
