package com.kodilla.ecommercee.service;

import com.kodilla.ecommercee.dto.request.CreateOrderRequest;
import com.kodilla.ecommercee.dto.request.UpdateOrderRequest;
import com.kodilla.ecommercee.dto.response.OrderResponse;
import com.kodilla.ecommercee.entity.Order;
import com.kodilla.ecommercee.entity.User;
import com.kodilla.ecommercee.entity.Cart;
import com.kodilla.ecommercee.exception.OrderNotFoundException;
import com.kodilla.ecommercee.mapper.OrderMapper;
import com.kodilla.ecommercee.repository.CartRepository;
import com.kodilla.ecommercee.repository.OrderRepository;
import com.kodilla.ecommercee.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


@Service
@RequiredArgsConstructor
public class OrderService {
    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private CartRepository cartRepository;    private final OrderMapper orderMapper;

    public List<Order> getAllOrders() {
        return orderRepository.findAll();
    }

    public OrderResponse getOrder(Long id) throws OrderNotFoundException {
        Order order = orderRepository.findById(id).orElseThrow(() -> new OrderNotFoundException(String.format("Order with id: %s not found", id)));
        return orderMapper.toOrderResponse(order);
    }

    public OrderResponse updateOrder(UpdateOrderRequest updateOrderRequest) throws OrderNotFoundException {
        Order order = orderRepository.findById(updateOrderRequest.id())
                .orElseThrow(() -> new OrderNotFoundException(String.format("Order with id: %s not found", updateOrderRequest.id())));
        order.setTotalPrice(updateOrderRequest.totalPrice());
        order.setStatus(updateOrderRequest.status());
        Order updatedOrder = orderRepository.save(order);
        return orderMapper.toOrderResponse(updatedOrder);
    }

    public OrderResponse createOrder (CreateOrderRequest createOrderRequest) {
        Optional<User> userOptional = userRepository.findById(createOrderRequest.userId());
        Optional<Cart> cartOptional = cartRepository.findById(createOrderRequest.cartId());

        if (userOptional.isPresent() && cartOptional.isPresent()) {
            User user = userOptional.get();
            Cart cart = cartOptional.get();

            Order order = new Order(createOrderRequest.totalPrice(), createOrderRequest.shippingAddress(), createOrderRequest.status(), user, cart);

            Order createdOrder = orderRepository.save(order);
            return orderMapper.toOrderResponse(createdOrder);
        } else {
            throw new IllegalArgumentException("User or Cart not found");
        }
    }

    public void deleteOrder(Long id) {
        if (orderRepository.existsById(id)) {
            orderRepository.deleteById(id);
        } else {
            throw new OrderNotFoundException(String.format("Order with id: %s not found", id));
        }
    }
}
