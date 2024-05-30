package com.hari.product.service;

import com.hari.product.model.ProductVO;

public interface ProductService {

	Long saveProduct(ProductVO productVO);

	void reduceQuantity(long productId, long quantity);

	ProductVO getProductDetails(long productId);

}
