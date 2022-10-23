package com.ecomm.productservice.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import com.ecomm.productservice.dto.ProductRequestDto;
import com.ecomm.productservice.dto.ProductResponseDto;
import com.ecomm.productservice.model.Product;
import com.ecomm.productservice.repository.ProductRepository;
import com.ecomm.productservice.service.ProductService;
import com.fasterxml.jackson.annotation.JsonTypeInfo.Id;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@RequiredArgsConstructor
@Slf4j
public class ProductServiceImpl implements ProductService{

	private final ProductRepository productRepository;
	
	public void createProduct(ProductRequestDto productRequest) {
		Product product=Product.builder()
				.name(productRequest.getName())
				.description(productRequest.getDescription())
				.price(productRequest.getPrice()).build();
		
		productRepository.save(product);
		log.info("Product {} is saved",product.getId());
		
	}

	public List<ProductResponseDto> getAllProducts() {
		List<Product> products= productRepository.findAll();
		
		//return products.stream().map(product -> mapToProductResponse(product)).toList();
		return products.stream().map(this::mapToProductResponse).toList();
		
	}
	
	private ProductResponseDto mapToProductResponse(Product product) {
		return ProductResponseDto.builder()
				.id(product.getId())
				.name(product.getName())
				.description(product.getDescription())
				.price(product.getPrice()).
				build();
	}
	
	
	
}
