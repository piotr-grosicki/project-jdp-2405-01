package com.kodilla.ecommercee.dto.response;

import java.time.LocalDateTime;

public record UserResponse(Long id, String username, String password, String address, Integer token, LocalDateTime tokenCreationTime, boolean isUserLocked) {
}
