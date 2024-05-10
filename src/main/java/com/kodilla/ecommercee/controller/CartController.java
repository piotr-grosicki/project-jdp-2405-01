package com.kodilla.ecommercee.controller;

import com.kodilla.ecommercee.dto.request.AddProductToCartRequest;
import com.kodilla.ecommercee.dto.request.CreateCartRequest;
import com.kodilla.ecommercee.dto.request.GetCartRequest;
import com.kodilla.ecommercee.dto.request.GetProductFromCartRequest;
import com.kodilla.ecommercee.dto.response.CartResponse;
import com.kodilla.ecommercee.dto.response.OrderResponse;
import com.kodilla.ecommercee.dto.response.ProductResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("shop/v1/cart")
public class CartController {

    @GetMapping
    public ResponseEntity<List<ProductResponse>> getAllProductFromCart(GetCartRequest getCartRequest) {
        return ResponseEntity.ok(List.of(new ProductResponse(1L, "product", "description")));
    }

    @PostMapping
    public ResponseEntity<CartResponse> createCart(CreateCartRequest createCartRequest) {
        return ResponseEntity.ok(new CartResponse(createCartRequest.userId(), new ArrayList<>(), null, null));
    }

    @PostMapping("/item")
    public ResponseEntity<CartResponse> addProductToCart(AddProductToCartRequest addProductToCartRequest) {
        return ResponseEntity.ok(new CartResponse(1L, new ArrayList<>(), addProductToCartRequest.quantity(), addProductToCartRequest.price()));
    }

    @PostMapping("/order")
    public ResponseEntity<OrderResponse> createOrderFromCart(GetCartRequest getCartRequest) {
        return ResponseEntity.ok(new OrderResponse(1L, getCartRequest.cartId(), 1L, BigDecimal.ZERO, "SEND"));

    }

    @DeleteMapping
    public ResponseEntity<ProductResponse> deleteProductFromCart(GetProductFromCartRequest getProductFromCartRequest) {
        return ResponseEntity.ok(new ProductResponse(getProductFromCartRequest.productId(), "product name", "product description"));
    }

}

