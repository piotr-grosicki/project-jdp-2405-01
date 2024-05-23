package com.kodilla.ecommercee.service;

import com.kodilla.ecommercee.dto.request.UpdateOrderRequest;
import com.kodilla.ecommercee.dto.response.OrderResponse;
import com.kodilla.ecommercee.entity.Order;
import com.kodilla.ecommercee.exception.NotEnoughPriceException;
import com.kodilla.ecommercee.exception.NullPriceException;
import com.kodilla.ecommercee.exception.OrderNotFoundException;
import com.kodilla.ecommercee.mapper.OrderMapper;
import com.kodilla.ecommercee.repository.OrderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

import static com.kodilla.ecommercee.entity.enums.OrderStatus.PAID;

@Service
@RequiredArgsConstructor
public class OrderService {
    private final OrderRepository orderRepository;
    private final OrderMapper orderMapper;

    public List<OrderResponse> getAllOrders() {
        List<Order> orders = orderRepository.findAll();
        return orderMapper.mapToOrderResponseList(orders);
    }

    public OrderResponse getOrder(Long id) {
        Order order = orderRepository.findById(id)
                .orElseThrow(() -> new OrderNotFoundException(id));
        return orderMapper.toOrderResponse(order);
    }

    public OrderResponse updateOrder(UpdateOrderRequest updateOrderRequest) {
        Order order = orderRepository.findById(updateOrderRequest.id())
                .orElseThrow(() -> new OrderNotFoundException(updateOrderRequest.id()));

        if (updateOrderRequest.totalPrice() == null) {
            throw new NullPriceException();
        }

        BigDecimal cartTotalPrice = order.getCart().getTotalProductPrice();
        if (updateOrderRequest.totalPrice().compareTo(cartTotalPrice) < 0) {
            throw new NotEnoughPriceException();
        }

        order.setTotalPrice(updateOrderRequest.totalPrice());
        order.setShippingAddress(updateOrderRequest.shippingAddress());
        if(updateOrderRequest.totalPrice().compareTo(cartTotalPrice) >= 0)
            order.setStatus(PAID);

        return orderMapper.toOrderResponse(orderRepository.save(order));
    }

    public void deleteOrder(Long id) {
        Order order = orderRepository.findById(id)
                .orElseThrow(() -> new OrderNotFoundException(id));
        orderRepository.delete(order);
    }
}
