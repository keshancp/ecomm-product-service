package com.ecomm.productservice.service;

import java.util.List;

import com.ecomm.productservice.dto.ProductDto;
import com.ecomm.productservice.dto.ProductRequestDto;
import com.ecomm.productservice.exception.ECommProductException;

public interface ProductService {

	ProductDto createProduct(ProductRequestDto productRequest, String traceId) throws ECommProductException;
	List<ProductDto> getAllProducts(String traceId);
	
}
