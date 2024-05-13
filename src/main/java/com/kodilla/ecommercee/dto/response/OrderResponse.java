package com.kodilla.ecommercee.dto.response;

import java.math.BigDecimal;

public record OrderResponse(Long id, Long cardId, Long userId, BigDecimal totalPrice, String status) {
}
