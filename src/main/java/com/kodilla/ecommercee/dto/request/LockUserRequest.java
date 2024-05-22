package com.kodilla.ecommercee.dto.request;

public record LockUserRequest(Long userId, String username, String password) {
}
