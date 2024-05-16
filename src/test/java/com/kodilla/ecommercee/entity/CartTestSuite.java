package com.kodilla.ecommercee.entity;

import com.kodilla.ecommercee.repository.CartRepository;
import com.kodilla.ecommercee.repository.ProductRepository;
import com.kodilla.ecommercee.repository.UserRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
public class CartTestSuite {

    @Autowired
    private CartRepository cartRepository;
    @Autowired
    private UserRepository userRepository;

    @AfterEach
    public void cleanUp() {
        cartRepository.deleteAll();
        userRepository.deleteAll();
    }

    @Test
    void shouldGetAllCarts() {
        //Given
        User user = new User("test", "test", "test");
        Cart cart = new Cart(new ArrayList<>(), user, BigDecimal.ONE, false);
        Cart cart2 = new Cart(new ArrayList<>(), user, BigDecimal.ONE, true);
        Cart cart3 = new Cart(new ArrayList<>(), user, BigDecimal.ONE, false);

        userRepository.save(user);
        cartRepository.save(cart);
        cartRepository.save(cart2);
        cartRepository.save(cart3);

        //When
        List<Cart> carts = cartRepository.findAll();

        //Then
        assertEquals(3, carts.size());
    }

    @Test
    void shouldGetOneCart() {
        //Given
        User user = new User("test", "test", "test");
        Cart cart = new Cart(new ArrayList<>(), user, BigDecimal.ONE, false);
        Cart cart2 = new Cart(new ArrayList<>(), user, BigDecimal.ONE, true);

        userRepository.save(user);
        cartRepository.save(cart);
        cartRepository.save(cart2);

        //When
        Optional<Cart> expectedCart = cartRepository.findById(cart.getId());

        //Then
        assertTrue(expectedCart.isPresent());
    }

    @Test
    void shouldCreateCart() {
        //Given
        User user = new User("test", "test", "test");
        Cart cart = new Cart(new ArrayList<>(), user, BigDecimal.ONE, false);

        userRepository.save(user);

        //When
        cartRepository.save(cart);
        Optional<Cart> expectedCart = cartRepository.findById(cart.getId());

        //Then
        assertTrue(expectedCart.isPresent());
    }

    @Test
    void shouldUpdateCart() {
        //Given
        User user = new User("test", "test", "test");
        Cart cart = new Cart(new ArrayList<>(), user, BigDecimal.ONE, false);

        userRepository.save(user);
        cartRepository.save(cart);

        //When
        cart.setIsActive(true);
        cartRepository.save(cart);
        Optional<Cart> expectedCart = cartRepository.findById(cart.getId());

        //Then
        assertTrue(expectedCart.get().getIsActive());
    }

    @Test
    void shouldDeleteCart() {
        //Given
        User user = new User("test", "test", "test");
        Cart cart = new Cart(new ArrayList<>(), user, BigDecimal.ONE, false);

        userRepository.save(user);
        cartRepository.save(cart);

        //When
        cartRepository.delete(cart);
        Optional<Cart> expectedCart = cartRepository.findById(cart.getId());

        //Then
        assertEquals(Optional.empty(), expectedCart);
    }
}
