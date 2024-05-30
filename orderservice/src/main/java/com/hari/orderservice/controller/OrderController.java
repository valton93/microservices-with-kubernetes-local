package com.hari.orderservice.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.hari.orderservice.model.OrderRequest;
import com.hari.orderservice.model.OrderResponse;
import com.hari.orderservice.service.OrderService;

@RestController
@RequestMapping("/v1/order")
public class OrderController {
	private static final Logger LOGGER
    = LoggerFactory.getLogger(OrderController.class);
	@Autowired
	private OrderService orderService;
	
	
	@PreAuthorize("hasAuthority('Customer')")
	@PostMapping("/placeOrder")
	public ResponseEntity<Long> placeOrder(@RequestBody OrderRequest orderRequest){
				
		Long orderId=orderService.saveOrder(orderRequest);
		LOGGER.info("Order Id: {}",orderId);
		return new ResponseEntity<>(orderId,HttpStatus.OK);
//		return ResponseEntity.status(HttpStatus.OK).body(order.getId());
	}
	
	@GetMapping("/{orderId}")
	public ResponseEntity<OrderResponse> getOrderDetails(@PathVariable(name ="orderId" ) long orderId){
		
		OrderResponse orderResponse=orderService.getOrderDetails(orderId);
		return new ResponseEntity<>(orderResponse,HttpStatus.OK);
	}

}
