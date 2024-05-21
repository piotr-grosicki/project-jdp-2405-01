package com.kodilla.ecommercee.exception;

public class InvalidCredentialsException extends Exception {
    public InvalidCredentialsException() {
        super("Invalid credentials");
    }
}
