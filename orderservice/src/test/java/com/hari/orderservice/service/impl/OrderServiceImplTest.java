package com.hari.orderservice.service.impl;

import static org.mockito.Mockito.when;

import java.time.Instant;
import java.util.Optional;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.web.client.RestTemplate;

import com.hari.orderservice.entity.Order;
import com.hari.orderservice.external.PaymentService;
import com.hari.orderservice.external.ProductService;
import com.hari.orderservice.model.OrderResponse;
import com.hari.orderservice.model.OrderResponse.PaymentResponse;
import com.hari.orderservice.model.OrderResponse.ProductVO;
import com.hari.orderservice.model.PaymentMode;
import com.hari.orderservice.repo.OrderRepo;
import com.hari.orderservice.service.OrderService;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.*;

@SpringBootTest
public class OrderServiceImplTest {
	
	@Mock
	private OrderRepo orderRepo;
	
	@Mock
	private ProductService productService;
	
	@Mock
	private PaymentService paymentService;
	
	@Mock
	private RestTemplate restTemplate;
	
	@InjectMocks
	OrderService orderService=new OrderServiceImpl();
	

	@DisplayName("Get Order- Success Scenario")
	@Test
	void test_When_Order_Success() {
		Order order=getMockOrder();
		when(orderRepo.findById(anyLong()))
        .thenReturn(Optional.of(order));
		
		 when(restTemplate.getForObject(
				 "http://PRODUCT-SERVICE/v1/product/"+order.getProductId(),
	                ProductVO.class
	        )).thenReturn(getMockProductResponse());

	        when(restTemplate.getForObject(
	        		"http://PAYMENT-SERVICE/v1/payment/getPaymentDetailsByOrderId?orderId="+order.getId(),
	                PaymentResponse.class
	        )).thenReturn(getMockPaymentResponse());
	        
	        
	        //Actual
	        OrderResponse orderResponse = orderService.getOrderDetails(1);
	        
	        //Verification
	        verify(orderRepo, times(1)).findById(anyLong());
	        verify(restTemplate, times(1)).getForObject(
	        		 "http://PRODUCT-SERVICE/v1/product/"+order.getProductId(),
	                ProductVO.class);
	        verify(restTemplate, times(1)).getForObject(
	        		"http://PAYMENT-SERVICE/v1/payment/getPaymentDetailsByOrderId?orderId="+order.getId(),
	                PaymentResponse.class);
	        
	      //Assert
	        assertNotNull(orderResponse);
	        assertEquals(order.getId(), orderResponse.getOrderId());

	        
	        
		
	}


	 private PaymentResponse getMockPaymentResponse() {
		return PaymentResponse.builder()
         .paymentId(1)
         .paymentDate(Instant.now())
         .modeOfPayment(PaymentMode.CASH.toString())
         .amount(200)
         .paymentStatus("ACCEPTED")
         .build();
	}


	private ProductVO getMockProductResponse() {
		  return ProductVO.builder()
	                .productId(2l)
	                .productName("iPhone")
	                .stockQuantity(200l)
	                .build();
	}


	private Order getMockOrder() {
	        return Order.builder()
	                .status("PLACED")
	                .id(1l)
	                .orderName("Order1")
	                .productId(1l)
	                .totalAmount(1000.0)
	                .build();
	    }

}
