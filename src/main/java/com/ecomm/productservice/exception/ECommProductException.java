package com.ecomm.productservice.exception;

public class ECommProductException extends Exception{

    public ECommProductException(String message) {
        super(message);
    }

    public ECommProductException(String message, Throwable cause) {
        super(message, cause);
    }
}
