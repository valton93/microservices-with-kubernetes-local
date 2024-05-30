package com.example.paymentservice.entity;

import java.time.Instant;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "transaction_details", schema="paymentdb")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class TransactionDetails {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private long id;
	private long orderId;
	private String modeOfPayment;
	private String referenceNumber;
	private Instant paymentDate;
	private String paymentStatus;
	private long amount;
	

}
