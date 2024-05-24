package com.kodilla.ecommercee.exception;

public class NullValueException extends RuntimeException {
    public NullValueException() {
        super("Null value is not allowed");
    }
}
