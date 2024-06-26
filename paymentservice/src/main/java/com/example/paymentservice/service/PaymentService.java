package com.example.paymentservice.service;

import com.example.paymentservice.model.PaymentRequest;
import com.example.paymentservice.model.PaymentResponse;

public interface PaymentService {

	Long doPayment(PaymentRequest paymentRequest);

	PaymentResponse getPaymentDetailsByOrderId(long orderId);

}
