package com.kodilla.ecommercee.dto.response;

public record UserLockedResponse(Long userId, String username, boolean isUserLocked) {
}
