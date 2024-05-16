package com.kodilla.ecommercee.entity;

import com.kodilla.ecommercee.repository.CartRepository;
import com.kodilla.ecommercee.repository.OrderRepository;
import com.kodilla.ecommercee.repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
public class OrderTestSuite {

    @Autowired
    private OrderRepository orderRepository;
    @Autowired
    private CartRepository cartRepository;
    @Autowired
    private UserRepository userRepository;

    @Test
    void shouldGetAllOrders() {
        //Given
        Order order = Order.builder().build();
        Order order2 = Order.builder().build();
        Order order3 = Order.builder().build();
        Order order4 = Order.builder().build();

        orderRepository.save(order);
        orderRepository.save(order2);
        orderRepository.save(order3);
        orderRepository.save(order4);

        //When
        List<Order> orders = orderRepository.findAll();

        //Then
        assertEquals(4, orders.size());

        //Cleanup
        orderRepository.delete(order);
        orderRepository.delete(order2);
        orderRepository.delete(order3);
        orderRepository.delete(order4);
    }

    @Test
    void shouldGetOneOrder() {
        //Given
        Order order = Order.builder().build();
        Order order2 = Order.builder().build();

        orderRepository.save(order);
        orderRepository.save(order2);

        //When
        Optional<Order> expectedOrder = orderRepository.findById(order.getId());

        //Then
        assertTrue(expectedOrder.isPresent());

        //Cleanup
        orderRepository.delete(order);
        orderRepository.delete(order2);
    }

    @Test
    void shouldCreateNewOrder() {
        //Given
        User user = User.builder()
                .id(1L)
                .build();

        Cart cart = Cart.builder()
                .id(1L)
                .user(user)
                .build();

        Order order = Order.builder()
                .id(1L)
                .totalPrice(BigDecimal.ONE)
                .shippingAddress("test address")
                .status(false)
                .user(user)
                .cart(cart)
                .build();

        userRepository.save(user);
        cartRepository.save(cart);
        orderRepository.save(order);

        //When
        Optional<Order> expectedOrder = orderRepository.findById(order.getId());

        //Then
        assertTrue(expectedOrder.isPresent());

        //Cleanup
        orderRepository.delete(order);
        userRepository.delete(user);
        cartRepository.delete(cart);
    }

    @Test
    void shouldUpdateOrder() {
        //Given
        User user = User.builder()
                .id(1L)
                .build();

        Cart cart = Cart.builder()
                .id(1L)
                .user(user)
                .build();

        Order order = Order.builder()
                .id(1L)
                .totalPrice(BigDecimal.ONE)
                .shippingAddress("test address")
                .status(false)
                .user(user)
                .cart(cart)
                .build();

        userRepository.save(user);
        cartRepository.save(cart);
        orderRepository.save(order);
        order.setShippingAddress("new address");

        orderRepository.save(order);

        //When
        Optional<Order> expectedOrder = orderRepository.findById(order.getId());

        //Then
        assertEquals("new address", expectedOrder.get().getShippingAddress());

        //Cleanup
        orderRepository.delete(order);
        userRepository.delete(user);
        cartRepository.delete(cart);
    }
}
