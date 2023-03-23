package com.ecomm.productservice.controller;

import java.util.List;


import com.ecomm.productservice.dto.ECommProductResponse;
import com.ecomm.productservice.dto.ProductDto;
import com.ecomm.productservice.exception.ECommProductException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.ecomm.productservice.dto.ProductRequestDto;
import com.ecomm.productservice.service.impl.ProductServiceImpl;

import lombok.RequiredArgsConstructor;

import javax.validation.Valid;

@RestController
@RequestMapping("api/product")
@RequiredArgsConstructor
public class ProductController {

	@Autowired
	private ProductServiceImpl productService;
	
	@PostMapping 
	@ResponseStatus(HttpStatus.CREATED)
	public ResponseEntity<?> createProduct(@Valid @RequestBody ProductRequestDto productRequestDto, @RequestParam String traceId) throws ECommProductException {
		
		ProductDto productDto=productService.createProduct(productRequestDto,traceId);

		return new ResponseEntity<>(new ECommProductResponse(HttpStatus.CREATED.value(),"Product Successfully Saved",productDto),HttpStatus.CREATED);
	}
	
	@GetMapping
	@ResponseStatus(HttpStatus.OK)
	public List<ProductDto> getAllProducts(@RequestParam String traceId){
		return productService.getAllProducts(traceId);
		
	}
	
	
	
}
