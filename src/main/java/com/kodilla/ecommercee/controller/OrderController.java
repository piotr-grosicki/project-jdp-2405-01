package com.kodilla.ecommercee.controller;

import com.kodilla.ecommercee.dto.request.CreateOrderRequest;
import com.kodilla.ecommercee.dto.request.UpdateOrderRequest;
import com.kodilla.ecommercee.dto.response.OrderResponse;
import com.kodilla.ecommercee.entity.enums.OrderStatus;
import com.kodilla.ecommercee.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("shop/v1/order")
public class OrderController {

    private final OrderService service;

    @Autowired
    public OrderController(OrderService service) {
        this.service = service;
    }

    @GetMapping
    public ResponseEntity<List<OrderResponse>> getAllOrders() {
        List<OrderResponse> orders = service.getAllOrders();
        return ResponseEntity.ok(orders);
    }

    @GetMapping("/{id}")
    public ResponseEntity<OrderResponse> getOrder(@PathVariable Long id) {
        OrderResponse orderResponse = service.getOrder(id);
        return ResponseEntity.ok(orderResponse);
    }

    @PostMapping
    public ResponseEntity<OrderResponse> createOrder(@RequestBody CreateOrderRequest createOrderRequest) {
        OrderResponse orderResponse = service.createOrder(createOrderRequest);
        return ResponseEntity.status(201).body(orderResponse);
    }

    @PutMapping
    public ResponseEntity<String> updateOrder(@RequestBody UpdateOrderRequest updateOrderRequest) {
        OrderResponse orderResponse = service.updateOrder(updateOrderRequest);
        String message = orderResponse.status() == OrderStatus.PAID ? "Order paid" : "Order unpaid";
        return ResponseEntity.ok(message);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteOrder(@PathVariable Long id) {
        service.deleteOrder(id);
        return ResponseEntity.noContent().build();
    }
}
