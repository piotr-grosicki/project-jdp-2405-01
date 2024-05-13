package com.kodilla.ecommercee.dto.response;

import java.math.BigDecimal;
import java.util.List;

public record CartResponse(Long id, List<ProductResponse> productResponses, Integer quantity, BigDecimal totalPrice) {
}
