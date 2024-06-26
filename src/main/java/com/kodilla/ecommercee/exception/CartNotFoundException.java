package com.kodilla.ecommercee.exception;

public class CartNotFoundException extends Exception {
    public CartNotFoundException(Long id) {
        super(String.format("Cart with id: %s not found", id));
    }
}
