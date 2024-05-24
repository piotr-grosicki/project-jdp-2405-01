package com.kodilla.ecommercee.dto.response;

import com.kodilla.ecommercee.entity.Cart;
import com.kodilla.ecommercee.entity.Product;

public record CartItemResponse(Long id, Product product, Integer quantity, Cart cart) {
}
