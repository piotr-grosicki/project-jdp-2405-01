package com.kodilla.ecommercee.dto.response;

import com.kodilla.ecommercee.entity.enums.OrderStatus;

import java.math.BigDecimal;

public record OrderResponse(Long id, Long cartId, Long userId, BigDecimal totalPrice, OrderStatus status) {
}
