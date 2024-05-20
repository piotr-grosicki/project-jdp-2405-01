package com.kodilla.ecommercee.controller;

import com.kodilla.ecommercee.dto.request.AddProductToCartRequest;
import com.kodilla.ecommercee.dto.request.CreateCartRequest;
import com.kodilla.ecommercee.dto.request.RemoveProductFromCartRequest;
import com.kodilla.ecommercee.dto.response.CartResponse;
import com.kodilla.ecommercee.dto.response.OrderResponse;
import com.kodilla.ecommercee.dto.response.ProductResponse;
import com.kodilla.ecommercee.exception.CartNotFoundException;
import com.kodilla.ecommercee.exception.ProductNotFoundException;
import com.kodilla.ecommercee.exception.UserNotFoundException;
import com.kodilla.ecommercee.service.CartService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("shop/v1/cart")
public class CartController {

    private final CartService cartService;

    @GetMapping("{cartId}")
    public ResponseEntity<List<ProductResponse>> getAllProductsFromCart(@PathVariable Long cartId) {
        return ResponseEntity.ok(List.of(new ProductResponse(1L, "product", "description", BigDecimal.ZERO, 0)));
    }

    @PostMapping
    public ResponseEntity<CartResponse> createCart(@RequestBody CreateCartRequest createCartRequest) throws UserNotFoundException {
        return ResponseEntity.ok(cartService.createEmptyCart(createCartRequest));
    }

    @PutMapping("/item")
    public ResponseEntity<CartResponse> addProductToCart(@RequestBody AddProductToCartRequest addProductToCartRequest) throws CartNotFoundException, ProductNotFoundException {
        return ResponseEntity.ok(cartService.addProductToCart(addProductToCartRequest));
    }

    @PostMapping("/{cartId}")
    public ResponseEntity<OrderResponse> createOrderFromCart(@PathVariable Long cartId) {
        return ResponseEntity.ok(new OrderResponse(1L, cartId, 1L, BigDecimal.ZERO, "SEND"));

    }

    @DeleteMapping
    public ResponseEntity<ProductResponse> deleteProductFromCart(@RequestBody RemoveProductFromCartRequest removeProductFromCartRequest) {
        return ResponseEntity.ok(new ProductResponse(removeProductFromCartRequest.productId(), "product name", "product description", BigDecimal.ZERO, 0));
    }

}

