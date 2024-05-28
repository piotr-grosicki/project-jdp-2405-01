package com.kodilla.ecommercee.dto.request;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;

public record LockUserRequest(@Valid @NotNull Long userId, String username, String password) {
}
