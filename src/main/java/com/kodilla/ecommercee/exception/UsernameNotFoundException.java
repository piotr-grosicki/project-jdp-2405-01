package com.kodilla.ecommercee.exception;

public class UsernameNotFoundException extends Exception {
    public UsernameNotFoundException(String username) {
        super(String.format("User with username: %s not found", username));
    }
}
