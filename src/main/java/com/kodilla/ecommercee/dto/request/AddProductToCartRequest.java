package com.kodilla.ecommercee.dto.request;

import java.math.BigDecimal;

public record AddProductToCartRequest(Long cartId, Long productId, Integer quantity, BigDecimal price) {
}
