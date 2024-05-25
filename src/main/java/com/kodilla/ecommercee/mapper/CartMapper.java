package com.kodilla.ecommercee.mapper;

import com.kodilla.ecommercee.dto.response.CartResponse;
import com.kodilla.ecommercee.entity.Cart;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CartMapper {

    private final CartItemMapper cartItemMapper;

    public CartResponse mapToCartResponse(Cart cart) {
        return new CartResponse(
                cart.getId(),
                cart.getCartItems().stream().map(cartItemMapper::mapToCartItemResponse).toList(),
                cart.getUser().getId(),
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
