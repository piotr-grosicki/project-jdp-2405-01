package com.kodilla.ecommercee.exception;

public class InvalidTokenException extends Exception {
    public InvalidTokenException() {
        super("Invalid token");
    }
}
