package com.kodilla.ecommercee.dto.request;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;

public record UpdateOrderRequest(@Valid @NotNull Long id, BigDecimal totalPrice, String shippingAddress) {
}
