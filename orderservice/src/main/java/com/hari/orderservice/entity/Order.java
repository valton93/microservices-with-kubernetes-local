package com.hari.orderservice.entity;

import jakarta.persistence.Column;
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
@Table(name="order_details" ,schema="orderdb")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Order {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id")
	private Long id;
	
	@Column(name="order_Name")
	private String orderName;
	
	@Column(name="product_Id")
	private Long productId;
	
	@Column(name="total_Amount")
	private Double totalAmount;
	
	@Column(name="status")
	private String status;
	
	
	
}
