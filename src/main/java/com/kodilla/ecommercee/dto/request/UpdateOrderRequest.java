package com.kodilla.ecommercee.dto.request;

public record UpdateOrderRequest (Long id,Long cardId, Long userId, Double totalPrice, String status){
}
