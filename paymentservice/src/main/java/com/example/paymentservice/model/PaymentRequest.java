package com.example.paymentservice.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PaymentRequest {
	
	private long orderId;
	private String referenceNo;
	private long amount;
	
	private PaymentMode paymentMode;
	

}
