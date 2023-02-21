package com.ecomm.productservice.service;

import java.util.List;

import com.ecomm.ecommlib.exception.ECommException;
import com.ecomm.productservice.dto.ProductDto;
import com.ecomm.productservice.dto.ProductRequestDto;
import com.ecomm.productservice.dto.ProductResponseDto;

public interface ProductService {

	ProductDto createProduct(ProductRequestDto productRequest, String traceId) throws ECommException;
	List<ProductDto> getAllProducts(String traceId);
	
}
