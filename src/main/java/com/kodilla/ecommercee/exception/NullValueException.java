package com.kodilla.ecommercee.exception;

public class NullValueException extends Exception {
    public NullValueException() {
        super("Username, password or adress is null");
    }
}
