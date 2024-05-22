package com.kodilla.ecommercee.exception;

public class UserNotFoundException extends Exception {
    public UserNotFoundException(Long id) {
        super(String.format("User with id: %s not found", id));
    }
}
