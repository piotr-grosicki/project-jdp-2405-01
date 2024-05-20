package com.kodilla.ecommercee.dto.response;

import com.kodilla.ecommercee.entity.User;

import java.math.BigDecimal;
import java.util.List;

public record CartResponse(Long id, List<ProductResponse> productResponses, User user, BigDecimal totalPrice, Boolean isActive) {
}
