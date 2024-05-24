package com.kodilla.ecommercee.dto.request;

public record AddProductToCartRequest(Long cartId, Long productId, Integer quantity) {
}
