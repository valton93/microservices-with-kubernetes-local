package com.hari.orderservice.service;

import com.hari.orderservice.model.OrderRequest;
import com.hari.orderservice.model.OrderResponse;

public interface OrderService {

	Long saveOrder(OrderRequest orderRequest);

	OrderResponse getOrderDetails(long orderId);

}
