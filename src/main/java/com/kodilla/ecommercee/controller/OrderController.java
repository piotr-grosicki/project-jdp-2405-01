package com.kodilla.ecommercee.controller;

import com.kodilla.ecommercee.dto.request.CreateOrderRequest;
import com.kodilla.ecommercee.dto.request.UpdateOrderRequest;
import com.kodilla.ecommercee.dto.response.OrderResponse;
import com.kodilla.ecommercee.entity.Order;
import com.kodilla.ecommercee.exception.OrderNotFoundException;
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
    public List<Order> getAllOrders() {
        return service.getAllOrders();
    }

    @GetMapping("/{id}")
    public ResponseEntity<OrderResponse> getOrder(@PathVariable Long id) {
        try {
            OrderResponse orderResponse = service.getOrder(id);
            return ResponseEntity.ok(orderResponse);
        } catch (OrderNotFoundException e) {
            return ResponseEntity.status(404).body(null);
        }
    }

    @PostMapping
    public ResponseEntity<OrderResponse> createOrder(@RequestBody CreateOrderRequest createOrderRequest) {
        try {
            OrderResponse orderResponse = service.createOrder(createOrderRequest);
            return ResponseEntity.status(201).body(orderResponse);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(null);
        }
    }

    @PutMapping
    public ResponseEntity<OrderResponse> updateOrder(@RequestBody UpdateOrderRequest updateOrderRequest) {
        try {
            OrderResponse orderResponse = service.updateOrder(updateOrderRequest);
            return ResponseEntity.ok(orderResponse);
        } catch (OrderNotFoundException e) {
            return ResponseEntity.status(404).body(null);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteOrder(@PathVariable Long id) {
        try {
            service.deleteOrder(id);
            return ResponseEntity.noContent().build();
        } catch (OrderNotFoundException e) {
            return ResponseEntity.status(404).build();
        }
    }
}

