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
    private Cart cart2;
    private Cart cart3;
    private Cart cart4;

    @BeforeEach
    public void setUp() {
        user = new User(1L);
        cart = new Cart(1L);
        cart2 = new Cart(2L);
        cart3 = new Cart(3L);
        cart4 = new Cart(4L);

        userRepository.save(user);

        cartRepository.save(cart);
        cartRepository.save(cart2);
        cartRepository.save(cart3);
        cartRepository.save(cart4);
    }

    @AfterEach
    public void cleanUp() {
        userRepository.delete(user);

        cartRepository.delete(cart);
        cartRepository.delete(cart2);
        cartRepository.delete(cart3);
        cartRepository.delete(cart4);
    }

    @Test
    void shouldGetAllOrders() {
        //Given
        Order order = new Order(1L, BigDecimal.ONE, "test address", false, user, cart);
        Order order2 = new Order(2L, BigDecimal.ONE, "test address", false, user, cart2);
        Order order3 = new Order(3L, BigDecimal.ONE, "test address", false, user, cart3);
        Order order4 = new Order(4L, BigDecimal.ONE, "test address", false, user, cart4);

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
        Order order = new Order(1L, BigDecimal.ONE, "test address", false, user, cart);
        Order order2 = new Order(2L, BigDecimal.ONE, "test address", false, user, cart2);

        orderRepository.save(order);
        orderRepository.save(order2);

        //When
        Optional<Order> expectedOrder = orderRepository.findById(1L);

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
        Optional<Order> expectedOrder = orderRepository.findById(1L);

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
