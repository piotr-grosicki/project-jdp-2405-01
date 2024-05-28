package com.kodilla.ecommercee.controller;

import com.kodilla.ecommercee.dto.request.UpdateOrderRequest;
import com.kodilla.ecommercee.dto.response.OrderResponse;
import com.kodilla.ecommercee.entity.enums.OrderStatus;
import com.kodilla.ecommercee.exception.NullValueException;
import com.kodilla.ecommercee.service.OrderService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("shop/v1/order")
@RequiredArgsConstructor
public class OrderController {

    private final OrderService service;

    @GetMapping
    public ResponseEntity<List<OrderResponse>> getAllOrders(@RequestParam Integer token) {
        List<OrderResponse> orders = service.getAllOrders();
        return ResponseEntity.ok(orders);
    }

    @GetMapping("/{id}")
    public ResponseEntity<OrderResponse> getOrder(@PathVariable Long id, @RequestParam Integer token) {
        return ResponseEntity.ok(service.getOrder(id));
    }

    @PutMapping
    public ResponseEntity<String> updateOrder(@Valid @RequestBody UpdateOrderRequest updateOrderRequest, @RequestParam Integer token) {
        try {
            OrderResponse orderResponse = service.updateOrder(updateOrderRequest);
            String message = orderResponse.status() == OrderStatus.PAID ? "Order paid" : "Order unpaid";
            return ResponseEntity.ok(message);
        } catch (NullValueException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteOrder(@PathVariable Long id, @RequestParam Integer token) {
        service.deleteOrder(id);
        return ResponseEntity.noContent().build();
    }
}
