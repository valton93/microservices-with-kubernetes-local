package com.hari.product.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.hari.product.model.ProductVO;
import com.hari.product.service.ProductService;

import lombok.extern.log4j.Log4j2;

@RestController
@RequestMapping("/v1/product")
@Log4j2
public class ProductController {
	
	
	@Autowired
	private ProductService productService;
	
	@PreAuthorize("hasAuthority('Admin')")
	@PostMapping("/saveProduct")
	public ResponseEntity<Long> saveProduct(@RequestBody ProductVO productVO){
		
		Long id=productService.saveProduct(productVO);
		
		log.info("order id: {}",id);
		return new ResponseEntity<>(id,HttpStatus.OK);
	}
	
	
	@PreAuthorize("hasAuthority('Admin')||hasAuthority('Customer')||hasAuthority('SCOPE_internal')")
	@PutMapping("/reduceQuantity/{id}")
	public ResponseEntity<Long> reduceQuantity(@PathVariable("id")long productId, @RequestParam long quantity){
		
		productService.reduceQuantity(productId,quantity);
		
		log.info("Reduced Quantity in product id: {} by {}",productId,quantity);
		return new ResponseEntity<>(HttpStatus.OK);
	}
	
	
	@GetMapping("/{productId}")
	public ResponseEntity<ProductVO>getProductDetails(@PathVariable(name="productId") long productId){
		ProductVO productVO=productService.getProductDetails(productId);
		return new ResponseEntity<>(productVO,HttpStatus.OK);
		
	}
	

}
