package com.kodilla.ecommercee.dto.request;

import lombok.AllArgsConstructor;

import java.math.BigDecimal;

@AllArgsConstructor
public record CreateOrderRequest (Long cartId, Long userId, BigDecimal totalPrice, String shippingAddress, boolean status ){
}
