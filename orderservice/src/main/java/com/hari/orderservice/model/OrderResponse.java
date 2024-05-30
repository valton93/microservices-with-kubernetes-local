package com.hari.orderservice.model;

import java.time.Instant;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class OrderResponse {
	private long orderId;
	private Instant orderDate;
	private String orderStatus;
	private double amount;
	private ProductVO productVO;
	private PaymentResponse paymentResponse;
	
	
	@Data
	@AllArgsConstructor
	@NoArgsConstructor
	@Builder
	public static class ProductVO {
		
		private String productName;
		
		private Long stockQuantity;
		
		private Long productId;
		

	}
	
	@Data
	@AllArgsConstructor
	@NoArgsConstructor
	@Builder
	public static class PaymentResponse {
		private long paymentId;
		private String modeOfPayment;
		private String referenceNumber;
		private Instant paymentDate;
		private String paymentStatus;
		private long amount;

	}

	

}
