package com.kodilla.ecommercee.exception;

public class NullPriceException extends RuntimeException {
    public NullPriceException() {
        super("Total price cannot be null");
    }
}
