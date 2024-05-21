package com.kodilla.ecommercee.service;

import com.kodilla.ecommercee.dto.request.CreateOrderRequest;
import com.kodilla.ecommercee.dto.request.UpdateOrderRequest;
import com.kodilla.ecommercee.dto.response.OrderResponse;
import com.kodilla.ecommercee.entity.Order;
import com.kodilla.ecommercee.entity.User;
import com.kodilla.ecommercee.entity.Cart;
import com.kodilla.ecommercee.exception.OrderNotFoundException;
import com.kodilla.ecommercee.exception.UserNotFoundException;
import com.kodilla.ecommercee.exception.CartNotFoundException;
import com.kodilla.ecommercee.exception.NotEnoughPriceException;
import com.kodilla.ecommercee.exception.InvalidPriceException;
import com.kodilla.ecommercee.mapper.OrderMapper;
import com.kodilla.ecommercee.repository.CartRepository;
import com.kodilla.ecommercee.repository.OrderRepository;
import com.kodilla.ecommercee.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

@Service
@RequiredArgsConstructor
public class OrderService {
    private final OrderRepository orderRepository;
    private final UserRepository userRepository;
    private final CartRepository cartRepository;
    private final OrderMapper orderMapper;

    public List<OrderResponse> getAllOrders() {
        List<Order> orders = orderRepository.findAll();
        return orders.stream().map(orderMapper::toOrderResponse).toList();
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
            throw new InvalidPriceException();
        }

        BigDecimal cartTotalPrice = order.getCart().getTotalProductPrice();
        if (updateOrderRequest.totalPrice().compareTo(cartTotalPrice) < 0) {
            throw new NotEnoughPriceException();
        }

        order.setTotalPrice(updateOrderRequest.totalPrice());
        order.setShippingAddress(updateOrderRequest.shippingAddress());
        order.setStatus(updateOrderRequest.totalPrice().compareTo(cartTotalPrice) >= 0);

        Order updatedOrder = orderRepository.save(order);
        return orderMapper.toOrderResponse(updatedOrder);
    }

    public OrderResponse createOrder(CreateOrderRequest createOrderRequest) {
        User user = userRepository.findById(createOrderRequest.userId())
                .orElseThrow(() -> new UserNotFoundException(createOrderRequest.userId()));
        Cart cart = cartRepository.findById(createOrderRequest.cartId())
                .orElseThrow(() -> new CartNotFoundException(createOrderRequest.cartId()));

        BigDecimal totalPrice = cart.getTotalProductPrice();
        String shippingAddress = user.getAddress();
        boolean status = false;

        Order order = new Order(totalPrice, shippingAddress, status, user, cart);
        Order createdOrder = orderRepository.save(order);
        return orderMapper.toOrderResponse(createdOrder);
    }

    public void deleteOrder(Long id) {
        Order order = orderRepository.findById(id)
                .orElseThrow(() -> new OrderNotFoundException(id));
        orderRepository.delete(order);
    }
}
