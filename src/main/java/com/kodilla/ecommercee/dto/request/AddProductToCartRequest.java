package com.kodilla.ecommercee.dto.request;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;

public record AddProductToCartRequest(@Valid @NotNull Long cartId, @Valid @NotNull Long productId, Integer quantity) {
}
