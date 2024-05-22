package com.kodilla.ecommercee.dto.request;

import com.kodilla.ecommercee.entity.Group;
import java.math.BigDecimal;

public record UpdateProductRequest(Long id, String name, String description, BigDecimal price, Integer quantity, Group group) {
}
