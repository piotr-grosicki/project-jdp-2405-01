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
        User user = User.builder()
                .id(1L)
                .build();
        User user2 = User.builder()
                .id(2L)
                .build();
        User user3 = User.builder()
                .id(3L)
                .build();

        Cart cart = Cart.builder()
                .id(1L)
                .user(user)
                .build();
        Cart cart2 = Cart.builder()
                .id(2L)
                .user(user2)
                .build();
        Cart cart3 = Cart.builder()
                .id(3L)
                .user(user3)
                .build();

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
        User user = User.builder()
                .id(1L)
                .build();
        User user2 = User.builder()
                .id(2L)
                .build();

        Cart cart = Cart.builder()
                .id(1L)
                .user(user)
                .build();
        Cart cart2 = Cart.builder()
                .id(2L)
                .user(user2)
                .build();

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
        User user = User.builder()
                .id(1L)
                .build();

        Product product = Product.builder()
                .id(1L)
                .name("product")
                .description("product")
                .price(new BigDecimal("9.99"))
                .quantity(1)
                .build();

        Cart cart = Cart.builder()
                .id(1L)
                .products(List.of(product))
                .user(user)
                .totalProductPrice(new BigDecimal("9.99"))
                .isActive(false)
                .build();

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
        User user = User.builder()
                .id(1L)
                .build();

        Product product = Product.builder()
                .id(1L)
                .name("product")
                .description("product")
                .price(new BigDecimal("9.99"))
                .quantity(1)
                .build();

        Cart cart = Cart.builder()
                .id(1L)
                .products(List.of(product))
                .user(user)
                .totalProductPrice(new BigDecimal("9.99"))
                .isActive(false)
                .build();

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
