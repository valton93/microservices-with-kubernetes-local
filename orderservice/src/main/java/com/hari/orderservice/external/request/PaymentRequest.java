package com.hari.orderservice.external.request;

import com.hari.orderservice.model.PaymentMode;

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
