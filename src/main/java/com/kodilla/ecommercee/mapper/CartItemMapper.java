package com.kodilla.ecommercee.mapper;

import com.kodilla.ecommercee.dto.response.CartItemResponse;
import com.kodilla.ecommercee.entity.CartItem;
import org.springframework.stereotype.Service;

@Service
public class CartItemMapper {

    public CartItemResponse mapToCartItemResponse(CartItem cartItem) {
        return new CartItemResponse(
                cartItem.getId(),
                cartItem.getProduct(),
                cartItem.getQuantity(),
                cartItem.getCart()
        );
    }
}
