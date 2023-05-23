package com.example.restapi.Exceptions;

public class ProductNotFoundException extends RuntimeException{
    public ProductNotFoundException(Long id) {
        super("Could not find order with id: " + id);
    }
}
