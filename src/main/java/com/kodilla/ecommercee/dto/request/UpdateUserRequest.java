package com.kodilla.ecommercee.dto.request;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;

public record UpdateUserRequest(@Valid @NotNull Long id, String username, String password, String address) {
}
