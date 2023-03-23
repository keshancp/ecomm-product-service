package com.ecomm.productservice.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import com.ecomm.productservice.dto.ProductDto;
import com.ecomm.productservice.exception.ECommProductException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ecomm.productservice.dto.ProductRequestDto;
import com.ecomm.productservice.model.Product;
import com.ecomm.productservice.repository.ProductRepository;
import com.ecomm.productservice.service.ProductService;

@Service
public class ProductServiceImpl implements ProductService{

	@Autowired
	private ProductRepository productRepository;

	@Autowired
	private ObjectMapper objectMapper;
	
	@Override
	public ProductDto createProduct(ProductRequestDto productRequest,String traceId) throws ECommProductException {
		Product product=Product.builder()
				.name(productRequest.getName())
				.description(productRequest.getDescription())
				.price(productRequest.getPrice()).build();


		Product newProduct=productRepository.save(product);
		ProductDto newProductDto=objectMapper.convertValue(newProduct,ProductDto.class);

		return newProductDto;

	}

	@Override
	public List<ProductDto> getAllProducts(String traceId) {
		List<Product> products= productRepository.findAll();
		
		//return products.stream().map(product -> mapToProductResponse(product)).toList();
		return products.stream().map(this::mapToProductResponse).collect(Collectors.toList());
		
	}
	
	private ProductDto mapToProductResponse(Product product) {
		return ProductDto.builder()
				.id(product.getId())
				.name(product.getName())
				.description(product.getDescription())
				.price(product.getPrice()).
				build();
	}
	
	
	
}
