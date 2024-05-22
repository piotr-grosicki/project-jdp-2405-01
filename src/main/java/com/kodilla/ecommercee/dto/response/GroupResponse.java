package com.kodilla.ecommercee.dto.response;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.kodilla.ecommercee.entity.Product;

import java.util.List;

public record GroupResponse(Long id, String name, @JsonIgnoreProperties({"group"}) List<Product>products) {
}
