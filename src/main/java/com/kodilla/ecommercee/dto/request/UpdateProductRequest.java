package com.kodilla.ecommercee.dto.request;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;

public record UpdateProductRequest(@Valid @NotNull Long id, String name, String description, BigDecimal price, Integer quantity, @Valid @NotNull Long groupId) {
}
