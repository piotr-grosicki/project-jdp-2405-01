package com.kodilla.ecommercee.dto.request;

import java.math.BigDecimal;

public record CreateProductRequest(String name, String description, BigDecimal price, Integer quantity, Long groupId) {
}