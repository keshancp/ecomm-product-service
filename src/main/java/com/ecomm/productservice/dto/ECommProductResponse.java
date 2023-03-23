package com.ecomm.productservice.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ECommProductResponse {

    private int statusCode;
    private String message;
    private Object data;

    public ECommProductResponse(int statusCode, String message){
        this.statusCode=statusCode;
        this.message=message;
    }

    public ECommProductResponse(Object data){
        this.data=data;
    }

    public ECommProductResponse(String message){
        this.message=message;
    }


    public ECommProductResponse(String message, Object data){
        this.message=message;
        this.data=data;
    }

}
