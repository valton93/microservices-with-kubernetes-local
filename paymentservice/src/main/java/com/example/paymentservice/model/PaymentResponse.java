package com.example.paymentservice.model;

import java.time.Instant;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PaymentResponse {
	private long paymentId;
	private String modeOfPayment;
	private String referenceNumber;
	private Instant paymentDate;
	private String paymentStatus;
	private long amount;

}
