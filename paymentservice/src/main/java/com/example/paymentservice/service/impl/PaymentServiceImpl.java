package com.example.paymentservice.service.impl;

import java.time.Instant;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.paymentservice.entity.TransactionDetails;
import com.example.paymentservice.exception.PaymentCustomException;
import com.example.paymentservice.exception.util.CustomHttpStatus;
import com.example.paymentservice.model.PaymentRequest;
import com.example.paymentservice.model.PaymentResponse;
import com.example.paymentservice.repo.TransactionDetailsRepo;
import com.example.paymentservice.service.PaymentService;

import lombok.extern.log4j.Log4j2;

@Service
@Log4j2
@Transactional(readOnly = true)
public class PaymentServiceImpl implements PaymentService {
	
	@Autowired
	private TransactionDetailsRepo transactionDetailsRepo;

	@Override
	@Transactional
	public Long doPayment(PaymentRequest paymentRequest) {
		log.info("Recording payment Details: {}",paymentRequest);
		
		TransactionDetails td=new TransactionDetails().builder().orderId(paymentRequest.getOrderId())
									.amount(paymentRequest.getAmount())
									.modeOfPayment(paymentRequest.getPaymentMode().toString())
									.paymentStatus("SUCCESS")
									.paymentDate(Instant.now())
									.referenceNumber(paymentRequest.getReferenceNo())
									.build();
		
		transactionDetailsRepo.save(td);
		log.info("Payment Success with id: {}",td.getId());
		return td.getId();
	}

	@Override
	public PaymentResponse getPaymentDetailsByOrderId(long orderId) {
		 TransactionDetails transactionDetails=transactionDetailsRepo.findByOrderId(orderId).orElseThrow(()->new PaymentCustomException(CustomHttpStatus.PAYMENT_NOt_FOUND, CustomHttpStatus.PAYMENT_NOt_FOUND.reasonPhrase()));
		
		 return PaymentResponse.builder()
		 						.amount(transactionDetails.getAmount())
		 						.modeOfPayment(transactionDetails.getModeOfPayment())
		 						.paymentDate(transactionDetails.getPaymentDate())
		 						.paymentId(transactionDetails.getId())
		 						.paymentStatus(transactionDetails.getPaymentStatus())
		 						.referenceNumber(transactionDetails.getReferenceNumber())
		 						.build();
	}

}
