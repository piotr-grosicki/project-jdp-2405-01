package com.kodilla.ecommercee.dto.request;

import java.math.BigDecimal;

public record UpdateOrderRequest(Long id, BigDecimal totalPrice, String shippingAddress) {
}
