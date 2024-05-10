package com.kodilla.ecommercee.dto.response;

public record OrderResponse (Long id,Long cardId, Long userId, Double totalPrice, String status){
}
