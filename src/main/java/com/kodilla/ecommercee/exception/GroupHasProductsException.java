package com.kodilla.ecommercee.exception;

public class GroupHasProductsException extends Exception {
    public GroupHasProductsException(Long id) {
        super(String.format("Group with id: %s still has products in it and cannot be deleted", id));
    }
}
