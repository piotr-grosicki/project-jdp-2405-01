package com.kodilla.ecommercee.mapper;

import com.kodilla.ecommercee.dto.response.OrderResponse;
import com.kodilla.ecommercee.entity.Order;
import org.springframework.stereotype.Component;

@Component
public class OrderMapper {

    public OrderResponse toOrderResponse(Order order) {
        return new OrderResponse(
                order.getId(),
                order.getCart().getId(),
                order.getUser().getId(),
                order.getTotalPrice(),
                order.isStatus()
        );
    }
}
