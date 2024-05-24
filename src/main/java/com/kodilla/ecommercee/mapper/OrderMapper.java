package com.kodilla.ecommercee.mapper;

import com.kodilla.ecommercee.dto.response.OrderResponse;
import com.kodilla.ecommercee.entity.Order;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class OrderMapper {

    public OrderResponse toOrderResponse(Order order) {
        return new OrderResponse(
                order.getId(),
                order.getCart().getId(),
                order.getUser().getId(),
                order.getTotalPrice(),
                order.getStatus()
        );
    }

    public List<OrderResponse> mapToOrderResponseList(List<Order> orders) {
        return orders.stream()
                .map(this::toOrderResponse)
                .collect(Collectors.toList());
    }
}
