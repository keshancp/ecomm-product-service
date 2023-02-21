package com.ecomm.productservice.dto;

import java.math.BigDecimal;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ProductRequestDto {

	@NotBlank(message = "Product Name is mandatory")
	private String name;
	private String description;
	private BigDecimal price;
	
	

}
