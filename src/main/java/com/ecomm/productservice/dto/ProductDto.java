package com.ecomm.productservice.dto;

import lombok.*;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ProductDto {

    private String id;
    private String name;
    private String description;
    private BigDecimal price;

}
