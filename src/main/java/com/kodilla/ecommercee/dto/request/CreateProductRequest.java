package com.kodilla.ecommercee.dto.request;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;

public record CreateProductRequest(String name, String description, BigDecimal price, Integer quantity, @Valid @NotNull Long groupId) {
}