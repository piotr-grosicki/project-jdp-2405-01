package com.kodilla.ecommercee.controller;

import com.kodilla.ecommercee.dto.request.CreateOrderRequest;
import com.kodilla.ecommercee.dto.request.UpdateOrderRequest;
import com.kodilla.ecommercee.dto.response.OrderResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("shop/v1/orders")
public class OrderController {

    @GetMapping
    public ResponseEntity<List<OrderResponse>> getAllOrders() {
        return ResponseEntity.ok(new ArrayList<>());
    }

    @GetMapping("/{id}")
    public ResponseEntity<OrderResponse> getOrder(@PathVariable Long id) {
        return ResponseEntity.ok(new OrderResponse(id, 2L, 3L, new BigDecimal("9.99"), "test status"));
    }

    @PostMapping
    public ResponseEntity<OrderResponse> createOrder(@RequestBody CreateOrderRequest createOrderRequest) {
        return ResponseEntity.ok(new OrderResponse(1L, 2L, 3L, new BigDecimal("10.99"), "created status"));
    }

    @PutMapping
    public ResponseEntity<OrderResponse> updateOrder(@RequestBody UpdateOrderRequest updateOrderRequest) {
        return ResponseEntity.ok(new OrderResponse(1L, 2L, 3L, new BigDecimal("11.99"), "updated status"));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<OrderResponse> deleteOrder(@PathVariable Long id) {
        return ResponseEntity.ok().build();
    }
}
