package com.ecomm.productservice.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.ecomm.productservice.dto.ProductRequestDto;
import com.ecomm.productservice.dto.ProductResponseDto;
import com.ecomm.productservice.service.impl.ProductServiceImpl;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("api/product")
@RequiredArgsConstructor
public class ProductController {

	
	private final ProductServiceImpl productService;
	
	@PostMapping 
	@ResponseStatus(HttpStatus.CREATED)
	public void createProduct(@RequestBody ProductRequestDto productRequestDto,@RequestParam String traceId) {
		
		productService.createProduct(productRequestDto,traceId);
		
	}
	
	@GetMapping
	@ResponseStatus(HttpStatus.OK)
	public List<ProductResponseDto> getAllProducts(@RequestParam String traceId){
		return productService.getAllProducts(traceId);
		
	}
	
	
	
}
