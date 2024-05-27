package com.kodilla.ecommercee.dto.request;

import com.kodilla.ecommercee.entity.Group;
import java.math.BigDecimal;

public record CreateProductRequest(String name, String description, BigDecimal price, Integer quantity, Long groupId) {
}