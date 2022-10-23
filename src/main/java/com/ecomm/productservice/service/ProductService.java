package com.ecomm.productservice.service;

import java.util.List;

import com.ecomm.productservice.dto.ProductRequestDto;
import com.ecomm.productservice.dto.ProductResponseDto;

public interface ProductService {

	void createProduct(ProductRequestDto productRequest,String traceId);
	List<ProductResponseDto> getAllProducts(String traceId);
	
}
