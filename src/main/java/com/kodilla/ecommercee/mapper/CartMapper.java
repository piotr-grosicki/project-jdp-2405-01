package com.kodilla.ecommercee.mapper;

import com.kodilla.ecommercee.dto.response.CartResponse;
import com.kodilla.ecommercee.entity.Cart;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CartMapper {

    private CartItemMapper cartItemMapper;

    public CartResponse mapToCartResponse(Cart cart) {
        return new CartResponse(
                cart.getId(),
                cart.getCartItems().stream().map(cartItemMapper::mapToCartItemResponse).toList(),
                cart.getUser(),
                cart.getTotalProductPrice(),
                cart.getIsActive()
        );
    }

    public List<CartResponse> mapToCartListResponse(List<Cart> carts) {
        return carts.stream()
                .map(this::mapToCartResponse)
                .toList();
    }
}
