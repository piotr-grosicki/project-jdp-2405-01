package com.kodilla.ecommercee.controller;

import com.kodilla.ecommercee.dto.request.AddProductToCartRequest;
import com.kodilla.ecommercee.dto.request.CreateCartRequest;
import com.kodilla.ecommercee.dto.request.RemoveProductFromCartRequest;
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

    @GetMapping("{cartId}")
    public ResponseEntity<List<ProductResponse>> getAllProductsFromCart(@PathVariable Long cartId) {
        return ResponseEntity.ok(List.of(new ProductResponse(1L, "product", "description")));
    }

    @PostMapping
    public ResponseEntity<CartResponse> createCart(@RequestBody CreateCartRequest createCartRequest) {
        return ResponseEntity.ok(new CartResponse(createCartRequest.userId(), new ArrayList<>(), null, null));
    }

    @PutMapping("/item")
    public ResponseEntity<CartResponse> addProductToCart(@RequestBody AddProductToCartRequest addProductToCartRequest) {
        return ResponseEntity.ok(new CartResponse(1L, new ArrayList<>(), addProductToCartRequest.quantity(), addProductToCartRequest.price()));
    }

    @PostMapping("/{cartId}")
    public ResponseEntity<OrderResponse> createOrderFromCart(@PathVariable Long cartId) {
        return ResponseEntity.ok(new OrderResponse(1L, cartId, 1L, BigDecimal.ZERO, true));

    }

    @DeleteMapping
    public ResponseEntity<ProductResponse> deleteProductFromCart(@RequestBody RemoveProductFromCartRequest removeProductFromCartRequest) {
        return ResponseEntity.ok(new ProductResponse(removeProductFromCartRequest.productId(), "product name", "product description"));
    }

}

