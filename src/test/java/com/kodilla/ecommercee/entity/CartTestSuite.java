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

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class CartTestSuite {

    @Autowired
    private CartRepository cartRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private ProductRepository productRepository;

    @AfterEach
    public void cleanUp() {
        cartRepository.deleteAll();
        productRepository.deleteAll();
        userRepository.deleteAll();
    }

    @Test
    void shouldGetAllCarts() {
        //Given
        User user = new User("test", "test", "test");

        Cart cart = new Cart(new ArrayList<>(), user, new BigDecimal("9.99"), false);
        Cart cart2 = new Cart(new ArrayList<>(), user, new BigDecimal("9.99"), false);

        userRepository.save(user);

        cartRepository.save(cart);
        cartRepository.save(cart2);

        //When
        List<Cart> carts = cartRepository.findAll();

        //Then
        assertEquals(2, carts.size());

        //Cleanup
        cartRepository.delete(cart);
        cartRepository.delete(cart2);
        userRepository.delete(user);
    }

    @Test
    void shouldGetOneCart() {
        //Given
        User user = new User("test", "test", "test");
        Cart cart = new Cart(new ArrayList<>(), user, new BigDecimal("9.99"), false);
        Cart cart2 = new Cart(new ArrayList<>(), user, new BigDecimal("9.99"), false);

        userRepository.save(user);


        cartRepository.save(cart);
        cartRepository.save(cart2);

        //When
        Optional<Cart> expectedCart = cartRepository.findById(cart.getId());

        //Then
        assertTrue(expectedCart.isPresent());

        //Cleanup
        cartRepository.delete(cart);
        cartRepository.delete(cart2);

        userRepository.delete(user);
    }

    @Test
    void shouldCreateCart() {
        //Given
        User user = new User("test", "test", "test");
        Cart cart = new Cart(new ArrayList<>(), user, new BigDecimal("9.99"), false);

        userRepository.save(user);

        //When
        cartRepository.save(cart);
        Optional<Cart> expectedCart = cartRepository.findById(cart.getId());

        //Then
        assertTrue(expectedCart.isPresent());

        //Cleanup
        cartRepository.delete(cart);
        userRepository.delete(user);
    }

    @Test
    void shouldUpdateCart() {
        //Given
        User user = new User("test", "test", "test");
        Cart cart = new Cart(new ArrayList<>(), user, new BigDecimal("9.99"), false);

        userRepository.save(user);
        cartRepository.save(cart);

        //When
        cart.setIsActive(true);
        cartRepository.save(cart);
        Optional<Cart> expectedCart = cartRepository.findById(cart.getId());

        //Then
        assertEquals(true, expectedCart.get().getIsActive());

        //Cleanup
        cartRepository.delete(cart);
        userRepository.delete(user);
    }

    @Test
    void shouldDeleteCart() {
        //Given
        User user = new User("test", "test", "test");
        Product product = new Product("test", "test", new BigDecimal("1.50"), 2);
        Cart cart = new Cart(List.of(product), user, new BigDecimal("9.99"), false);

        userRepository.save(user);
        productRepository.save(product);
        cartRepository.save(cart);

        //When
        cartRepository.delete(cart);
        Optional<Cart> expectedCart = cartRepository.findById(cart.getId());

        //Then
        assertEquals(Optional.empty(), expectedCart);
        assertNotNull(user);
        assertNotNull(product);
    }
}
