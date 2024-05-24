package com.kodilla.ecommercee.dto.response;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.kodilla.ecommercee.entity.Group;
import java.math.BigDecimal;

public record ProductResponse(Long id, String name, String description, BigDecimal price, Integer quantity,  @JsonIgnoreProperties({"productList"})Group group) {
}
