package com.hari.product.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.hari.product.entity.Product;

@Repository
public interface ProductRepo extends JpaRepository<Product,Long>{

}
