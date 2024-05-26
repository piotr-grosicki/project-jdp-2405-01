package com.kodilla.ecommercee.dto.response;

public record CartItemResponse(Long id, Long productId, Integer quantity, Long cartId) {
}
