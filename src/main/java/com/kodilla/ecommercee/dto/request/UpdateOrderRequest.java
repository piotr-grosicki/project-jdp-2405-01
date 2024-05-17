package com.kodilla.ecommercee.dto.request;

public record UpdateOrderRequest (Long id, Double totalPrice, String status){
}
