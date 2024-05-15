package com.kodilla.ecommercee.entity;

import com.kodilla.ecommercee.repository.CartRepository;
import com.kodilla.ecommercee.repository.OrderRepository;
import com.kodilla.ecommercee.repository.UserRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
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
    private User user;
    private Cart cart;

    @BeforeEach
    public void setUp() {
        user = new User(1L);
        cart = new Cart(1L);

        userRepository.save(user);
        cartRepository.save(cart);
    }

    @AfterEach
    public void cleanUp() {
        userRepository.delete(user);
        cartRepository.delete(cart);
    }

    @Test
    void shouldGetAllOrders() {
        //Given
        Order order = new Order();
        Order order2 = new Order();
        Order order3 = new Order();
        Order order4 = new Order();

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
        Order order = new Order();
        Order order2 = new Order();

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
        Order order = new Order(1L, BigDecimal.ONE, "test address", false, user, cart);

        orderRepository.save(order);

        //When
        Optional<Order> expectedOrder = orderRepository.findById(order.getId());

        //Then
        assertTrue(expectedOrder.isPresent());

        //Cleanup
        orderRepository.delete(order);
    }

    @Test
    void shouldUpdateOrder() {
        //Given
        Order order = new Order(1L, BigDecimal.ONE, "test address", false, user, cart);
        order.setShippingAddress("new address");

        orderRepository.save(order);

        //When
        Optional<Order> expectedOrder = orderRepository.findById(1L);

        //Then
        assertEquals("new address", expectedOrder.get().getShippingAddress());

        //Cleanup
        orderRepository.delete(order);
    }
}
