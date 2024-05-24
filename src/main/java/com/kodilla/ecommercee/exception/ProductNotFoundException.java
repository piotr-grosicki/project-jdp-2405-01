package com.kodilla.ecommercee.exception;

public class ProductNotFoundException extends Exception {
    public ProductNotFoundException(Long id){
        super(String.format("Product with id: %d could not be found", id));
    }
}