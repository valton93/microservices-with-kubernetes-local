package com.hari.orderservice.service.impl;

import java.time.Instant;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClientResponseException;
import org.springframework.web.client.RestTemplate;

import com.hari.orderservice.entity.Order;
import com.hari.orderservice.exception.OrderServiceCustomException;
import com.hari.orderservice.exception.util.CustomHttpStatus;
import com.hari.orderservice.external.PaymentService;
import com.hari.orderservice.external.ProductService;
import com.hari.orderservice.external.request.PaymentRequest;
import com.hari.orderservice.model.OrderRequest;
import com.hari.orderservice.model.OrderResponse;
import com.hari.orderservice.model.OrderResponse.PaymentResponse;
import com.hari.orderservice.model.OrderResponse.ProductVO;
import com.hari.orderservice.repo.OrderRepo;
import com.hari.orderservice.service.OrderService;

import lombok.extern.log4j.Log4j2;


@Service
@Log4j2
public class OrderServiceImpl implements OrderService {
	
	@Autowired
	private OrderRepo orderRepo;
	
	@Autowired
	private ProductService productService;
	
	@Autowired
	private PaymentService paymentService;
	
	@Autowired
	private RestTemplate restTemplate;

	@Override
	public Long saveOrder(OrderRequest orderRequest) {
		
		productService.reduceQuantity(orderRequest.getProductId() ,orderRequest.getQuantity());
		
		Order order=Order.builder().orderName(orderRequest.getOrderName())
				.productId(orderRequest.getProductId()).totalAmount(orderRequest.getTotalAmount())
				.status("CREATED")
				.build();
		orderRepo.save(order);
		String paymentStatus=null;
		try {
		log.info("Payment Initiated for order id: {}",order.getId());
		PaymentRequest paymentRequest=PaymentRequest.builder()
				.amount(100l)
				.orderId(order.getId())
				.paymentMode(orderRequest.getPaymentMode())
				.referenceNo(orderRequest.getOrderName())
				.build();
		paymentService.doPayment(paymentRequest);
		paymentStatus="PLACED";
		}catch (Exception e) {
			log.error("Payment Failed");
			paymentStatus="PAYMENT_FAILED";
		}
		order.setStatus(paymentStatus);

		orderRepo.save(order);
		return order.getId();
	}

	@Override
	public OrderResponse getOrderDetails(long orderId) {
		Order order = orderRepo.findById(orderId)
				.orElseThrow(() -> new OrderServiceCustomException(CustomHttpStatus.ORDER_NOT_FOUND,CustomHttpStatus.ORDER_NOT_FOUND.getReasonPhrase()));
		
		ProductVO productVO=restTemplate.getForObject("http://PRODUCT-SERVICE/v1/product/"+order.getProductId(), ProductVO.class);
		
		PaymentResponse paymentResponse=restTemplate.getForObject("http://PAYMENT-SERVICE/v1/payment/getPaymentDetailsByOrderId?orderId="+order.getId(), PaymentResponse.class);
		return OrderResponse.builder().orderStatus(order.getStatus()).orderId(order.getId()).productVO(productVO).paymentResponse(paymentResponse)
				.amount(order.getTotalAmount()).orderDate(Instant.now()).build();
	}

}
