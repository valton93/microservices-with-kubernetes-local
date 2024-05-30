package com.hari.product.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.hari.product.entity.Product;
import com.hari.product.exception.ProductServiceCustomException;
import com.hari.product.exception.utility.CustomHttpStatus;
import com.hari.product.model.ProductVO;
import com.hari.product.repo.ProductRepo;
import com.hari.product.service.ProductService;

@Service
@Transactional(readOnly = true)
public class ProductServiceImpl implements ProductService{
	
	@Autowired 
	private ProductRepo productRepo;

	@Override
	@Transactional
	public Long saveProduct(ProductVO productVO) {
		Product product=Product.builder().productName(productVO.getProductName()).stockQuantity(productVO.getStockQuantity())
		.build();
		productRepo.save(product);
		return product.getId();
	}
	@Override
	@Transactional
	public void reduceQuantity(long productId, long quantity) {
		Product product=productRepo.findById(productId).orElseThrow(
				()->new ProductServiceCustomException(CustomHttpStatus.PRODUCT_NOT_FOUND,CustomHttpStatus.PRODUCT_NOT_FOUND.getReasonPhrase())
				);
		if(product.getStockQuantity()<quantity) {
			throw new ProductServiceCustomException(CustomHttpStatus.PRODUCT_INSUFFICIENT_QUANTITY,CustomHttpStatus.PRODUCT_INSUFFICIENT_QUANTITY.getReasonPhrase());
		}
		
		product.setStockQuantity(product.getStockQuantity()-quantity);
		productRepo.save(product);
		
	}
	@Override
	public ProductVO getProductDetails(long productId) {
		Product product= productRepo.findById(productId)
						.orElseThrow(()->new ProductServiceCustomException(CustomHttpStatus.PRODUCT_NOT_FOUND, CustomHttpStatus.PRODUCT_NOT_FOUND.getReasonPhrase()));
		return	ProductVO.builder().productName(product.getProductName())
									.stockQuantity(product.getStockQuantity())
									.productId(product.getId())
									.build();
	}

}
