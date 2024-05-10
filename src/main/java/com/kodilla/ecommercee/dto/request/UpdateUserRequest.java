package com.kodilla.ecommercee.dto.request;

public record UpdateUserRequest(Long id, String username, String password, String address) {
}
