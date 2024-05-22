package com.kodilla.ecommercee.exception;

public class NullOrEmptyValueException extends Exception {
    public NullOrEmptyValueException() {
        super("Provided data is null or empty.");
    }
}
