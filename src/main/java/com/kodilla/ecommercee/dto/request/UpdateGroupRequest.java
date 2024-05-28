package com.kodilla.ecommercee.dto.request;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;

public record UpdateGroupRequest(@Valid @NotNull Long id, String name) {
}
