package com.kodilla.ecommercee.entity;

import com.kodilla.ecommercee.repository.CartRepository;
import com.kodilla.ecommercee.repository.ProductRepository;
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
public class CartTestSuite {

    @Autowired
    private CartRepository cartRepository;
    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private UserRepository userRepository;

    @Test
    void shouldGetAllCarts() {
        //Given
        User user = new User(1L);
        Cart cart = new Cart(1L, user);
        User user2 = new User(2L);
        Cart cart2 = new Cart(2L, user2);
        User user3 = new User(3L);
        Cart cart3 = new Cart(3L, user3);

        userRepository.save(user);
        userRepository.save(user2);
        userRepository.save(user3);

        cartRepository.save(cart);
        cartRepository.save(cart2);
        cartRepository.save(cart3);

        //When
        List<Cart> carts = cartRepository.findAll();

        //Then
        assertEquals(3, carts.size());

        //Cleanup
        cartRepository.delete(cart);
        cartRepository.delete(cart2);
        cartRepository.delete(cart3);
        userRepository.delete(user);
        userRepository.delete(user2);
        userRepository.delete(user3);
    }

    @Test
    void shouldGetOneCart() {
        //Given
        User user = new User(1L);
        Cart cart = new Cart(1L, user);
        User user2 = new User(2L);
        Cart cart2 = new Cart(2L, user2);

        userRepository.save(user);
        userRepository.save(user2);

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
        userRepository.delete(user2);
    }

    @Test
    void shouldCreateCart() {
        //Given
        User user = new User(1L);
        Product product = new Product(1L, "product", "product", new BigDecimal("9.99"), 1);
        Cart cart = new Cart(1L, List.of(product), user, new BigDecimal("9.99"), false);

        userRepository.save(user);
        productRepository.save(product);
        cartRepository.save(cart);

        //When
        Optional<Cart> expectedCart = cartRepository.findById(cart.getId());

        //Then
        assertTrue(expectedCart.isPresent());

        //Cleanup
        cartRepository.delete(cart);
        productRepository.delete(product);
        userRepository.delete(user);
    }

    @Test
    void shouldUpdateCart() {
        //Given
        User user = new User(1L);
        Product product = new Product(1L, "product", "product", new BigDecimal("9.99"), 1);
        Cart cart = new Cart(1L, List.of(product), user, new BigDecimal("9.99"), false);

        userRepository.save(user);
        productRepository.save(product);
        cartRepository.save(cart);

        cart.setIsActive(true);

        cartRepository.save(cart);

        //When
        Optional<Cart> expectedCart = cartRepository.findById(cart.getId());

        //Then
        assertEquals(true, expectedCart.get().getIsActive());

        //Cleanup
        cartRepository.delete(cart);
        productRepository.delete(product);
        userRepository.delete(user);
    }
}
