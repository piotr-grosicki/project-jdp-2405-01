package com.kodilla.ecommercee.entity;

import com.kodilla.ecommercee.repository.CartRepository;
import com.kodilla.ecommercee.repository.OrderRepository;
import com.kodilla.ecommercee.repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import java.util.List;

@SpringBootTest
public class UserTestSuite {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private OrderRepository orderRepository;
    @Autowired
    private CartRepository cartRepository;

    @Test
    void shouldGetAllUsers() {
        //given
        User user1 = new User();
        User user2 = new User();
        User user3 = new User();
        userRepository.save(user1);
        userRepository.save(user2);
        userRepository.save(user3);

        //when
        List<User> users = userRepository.findAll();

        //then
        assertEquals(3, users.size());

        //clean up
        userRepository.delete(user1);
        userRepository.delete(user2);
        userRepository.delete(user3);
    }

    @Test
    void shouldGetUserById() {
        //given
        User user1 = new User();
        User user2 = new User();
        userRepository.save(user1);
        userRepository.save(user2);

        //when
        User expectedUser1 = userRepository.findById(user1.getId()).orElse(null);

        //then
        assertThat(expectedUser1).isNotNull();
        assertEquals(expectedUser1.getId(), user1.getId());
        assertNotEquals(expectedUser1.getId(), user2.getId());

        //clean up
        userRepository.delete(user1);
        userRepository.delete(user2);
    }

    @Test
    void shouldDeleteUser() {
        //given
        User user1 = new User();
        User user2 = new User();
        User user3 = new User();
        userRepository.save(user1);
        userRepository.save(user2);
        userRepository.save(user3);

        //when
        userRepository.deleteById(user2.getId());
        Long size = userRepository.count();
        User expectedNullUser = userRepository.findById(user2.getId()).orElse(null);

        //then
        assertEquals(2, size);
        assertNull(expectedNullUser);

        //clean up
        userRepository.delete(user1);
        userRepository.delete(user3);
    }

    @Test
    void shouldCreateUser() {
        //given
        User user = new User();
        user.setUsername("New username");
        user.setPassword("New password");
        user.setAddress("New address");
        userRepository.save(user);

        Cart cart = new Cart();
        cart.setUser(user);
        cartRepository.save(cart);

        Order order1 = new Order();
        Order order2 = new Order();
        order1.setUser(user);
        order2.setUser(user);
        orderRepository.save(order1);
        orderRepository.save(order2);

        //when
        User testUser = userRepository.findById(user.getId()).orElse(null);

        //then
        assertNotNull(testUser);
        assertEquals("New username", testUser.getUsername());
        assertEquals(2, testUser.getOrders().size());

        //clean up
        orderRepository.delete(order1);
        orderRepository.delete(order2);
        cartRepository.delete(cart);
        userRepository.delete(user);
    }

    @Test
    void shouldUpdateUser() {
        //given
        User user = new User();
        user.setUsername("New username");
        user.setPassword("New password");
        user.setAddress("New address");
        userRepository.save(user);

        Cart cart = new Cart();
        cart.setUser(user);
        cartRepository.save(cart);

        Order order1 = new Order();
        Order order2 = new Order();
        Order order3 = new Order();
        order1.setUser(user);
        order2.setUser(user);
        orderRepository.save(order1);
        orderRepository.save(order2);

        //when
        order3.setUser(user);
        orderRepository.save(order3);
        user.setPassword("Updated password");
        userRepository.save(user);
        User testUser = userRepository.findById(user.getId()).orElse(null);

        //then
        assertEquals("Updated password", testUser.getPassword());
        assertEquals(3, testUser.getOrders().size());

        //clean up
        orderRepository.delete(order1);
        orderRepository.delete(order2);
        orderRepository.delete(order3);
        cartRepository.delete(cart);
        userRepository.delete(user);
    }


}
