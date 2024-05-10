package com.kodilla.ecommercee.dto.request;

public record CreateOrderRequest (Long cartId, Long userId, Double totalPrice, String status ){
}
