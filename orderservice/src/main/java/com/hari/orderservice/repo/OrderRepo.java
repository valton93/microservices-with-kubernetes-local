package com.hari.orderservice.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.hari.orderservice.entity.Order;

@Repository
public interface OrderRepo extends JpaRepository<Order,Long> {

}
