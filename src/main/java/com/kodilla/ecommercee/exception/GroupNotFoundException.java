package com.kodilla.ecommercee.exception;

public class GroupNotFoundException extends Exception {
    public GroupNotFoundException(Long id) {
        super(String.format("Group with id: %s could not be found", id));
    }
}
