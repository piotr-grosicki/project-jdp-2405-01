package com.kodilla.ecommercee.exception;

public class NullValueException extends Exception {
    public NullValueException() {
        super("Null value is not allowed");
    }
}
