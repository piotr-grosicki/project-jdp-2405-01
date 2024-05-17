package com.kodilla.ecommercee.dto.request;

import java.math.BigDecimal;

public record CreateOrderRequest (Long cartId, Long userId, BigDecimal totalPrice, Boolean status ){
}
