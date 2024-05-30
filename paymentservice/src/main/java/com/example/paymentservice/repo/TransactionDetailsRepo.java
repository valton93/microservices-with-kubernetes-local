package com.example.paymentservice.repo;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.paymentservice.entity.TransactionDetails;

public interface TransactionDetailsRepo extends JpaRepository<TransactionDetails, Long> {

	Optional<TransactionDetails> findByOrderId(long orderId);


}
