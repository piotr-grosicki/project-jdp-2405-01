package com.kodilla.ecommercee.exception;

public class InvalidPriceException extends RuntimeException {
    public InvalidPriceException() {
        super("Total price cannot be null");
    }
}
