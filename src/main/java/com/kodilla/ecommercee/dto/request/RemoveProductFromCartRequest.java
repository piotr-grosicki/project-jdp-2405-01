package com.kodilla.ecommercee.dto.request;

public record RemoveProductFromCartRequest(Long cartId, Long productId) {
}
