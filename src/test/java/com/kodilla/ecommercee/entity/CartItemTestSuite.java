package com.kodilla.ecommercee.entity;

import com.kodilla.ecommercee.repository.CartItemRepository;
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
public class CartItemTestSuite {

    @Autowired
    CartItemRepository cartItemRepository;
    @Autowired
    CartRepository cartRepository;
    @Autowired
    ProductRepository productRepository;
    @Autowired
    UserRepository userRepository;

    @AfterEach
    void cleanup() {
        cartItemRepository.deleteAll();
        cartRepository.deleteAll();
        productRepository.deleteAll();
        productRepository.deleteAll();
    }

    ArrayList<User> createUserList() {
        return new ArrayList<>(List.of(
                new User("test", "test", "test"),
                new User("test", "test", "test"),
                new User("test", "test", "test")
        ));
    }

    ArrayList<Product> createProductList() {
        return new ArrayList<>(List.of(
                new Product("test", "test", new BigDecimal("1.50"), 2),
                new Product("test", "test", new BigDecimal("1.50"), 2),
                new Product("test", "test", new BigDecimal("1.50"), 2)
        ));
    }

    ArrayList<Cart> createCartList(List<User> userList) {
        return new ArrayList<>(List.of(
                new Cart(List.of(), userList.get(0), new BigDecimal("9.99"), false),
                new Cart(List.of(), userList.get(1), new BigDecimal("9.99"), false),
                new Cart(List.of(), userList.get(2), new BigDecimal("9.99"), false)
        ));
    }

    @Test
    void shouldGetAllCartItems() {
        //Given
        ArrayList<User> userList = createUserList();
        ArrayList<Product> productList = createProductList();
        ArrayList<Cart> cartList = createCartList(userList);

        List<CartItem> cartItemList = List.of(
                new CartItem(productList.get(0), 2, cartList.get(0)),
                new CartItem(productList.get(1), 2, cartList.get(1)),
                new CartItem(productList.get(2), 2, cartList.get(2))
        );

        userRepository.saveAll(userList);
        productRepository.saveAll(productList);

        cartList.get(0).setCartItems(List.of(cartItemList.get(0)));
        cartList.get(1).setCartItems(List.of(cartItemList.get(1)));
        cartList.get(2).setCartItems(List.of(cartItemList.get(2)));

        cartRepository.saveAll(cartList);

        //When
        List<CartItem> expectedCartItemList = cartItemRepository.findAll();

        //Then
        assertEquals(3, expectedCartItemList.size());

        //Cleanup with AfterEach
    }

    @Test
    void shouldGetCartItemById() {
        //Given
        ArrayList<User> userList = createUserList();
        ArrayList<Product> productList = createProductList();
        ArrayList<Cart> cartList = createCartList(userList);

        List<CartItem> cartItemList = List.of(
                new CartItem(productList.get(0), 2, cartList.get(0)),
                new CartItem(productList.get(1), 2, cartList.get(1)),
                new CartItem(productList.get(2), 2, cartList.get(2))
        );

        userRepository.saveAll(userList);
        productRepository.saveAll(productList);

        cartList.get(0).setCartItems(List.of(cartItemList.get(0)));
        cartList.get(1).setCartItems(List.of(cartItemList.get(1)));
        cartList.get(2).setCartItems(List.of(cartItemList.get(2)));

        cartRepository.saveAll(cartList);

        //When
        Optional<CartItem> expectedCartItem = cartItemRepository.findById(cartItemList.get(1).getId());

        //Then
        assertTrue(expectedCartItem.isPresent());

        //Cleanup with AfterEach
    }

    @Test
    void shouldCreateCartItem() {
        //Given
        ArrayList<User> userList = createUserList();
        ArrayList<Product> productList = createProductList();
        ArrayList<Cart> cartList = createCartList(userList);

        List<CartItem> cartItemList = List.of(
                new CartItem(productList.get(0), 2, cartList.get(0)),
                new CartItem(productList.get(1), 2, cartList.get(1)),
                new CartItem(productList.get(2), 2, cartList.get(2))
        );

        userRepository.saveAll(userList);
        productRepository.saveAll(productList);
        cartRepository.saveAll(cartList);

        //When
        cartItemRepository.saveAll(cartItemList);
        List<CartItem> expectedCartItemListInCart = cartRepository.findById(cartList.get(1).getId()).get().getCartItems();
        List<CartItem> expectedCartItemList = cartItemRepository.findAll();

        //Then
        assertEquals(3, expectedCartItemList.size());
        assertEquals(1, expectedCartItemListInCart.size());

        //Cleanup with AfterEach
        }

    @Test
    void shouldUpdateCartItem() {
        //Given
        ArrayList<User> userList = createUserList();
        ArrayList<Product> productList = createProductList();
        ArrayList<Cart> cartList = createCartList(userList);

        List<CartItem> cartItemList = List.of(
                new CartItem(productList.get(0), 2, cartList.get(0)),
                new CartItem(productList.get(1), 2, cartList.get(1)),
                new CartItem(productList.get(2), 2, cartList.get(2))
        );

        userRepository.saveAll(userList);
        productRepository.saveAll(productList);
        cartRepository.saveAll(cartList);

        //When
        cartItemList.get(0).setQuantity(5);
        cartItemRepository.save(cartItemList.get(0));
        Optional<CartItem> expectedCartItem = cartItemRepository.findById(cartItemList.get(0).getId());

        //Then
        assertEquals(5, expectedCartItem.get().getQuantity());

        //Cleanup with AfterEach
    }

    @Test
    void shouldDeleteCartItem() {
        //Given
        ArrayList<User> userList = createUserList();
        ArrayList<Product> productList = createProductList();
        ArrayList<Cart> cartList = createCartList(userList);

        List<CartItem> cartItemList = List.of(
                new CartItem(productList.get(0), 2, cartList.get(0)),
                new CartItem(productList.get(1), 2, cartList.get(1)),
                new CartItem(productList.get(2), 2, cartList.get(2))
        );

        userRepository.saveAll(userList);
        productRepository.saveAll(productList);

        cartList.get(0).setCartItems(List.of(cartItemList.get(0)));
        cartList.get(1).setCartItems(List.of(cartItemList.get(1)));
        cartList.get(2).setCartItems(List.of(cartItemList.get(2)));

        cartRepository.saveAll(cartList);

        //When
        cartList.get(0).setCartItems(new ArrayList<>());
        cartRepository.save(cartList.get(0));

        List<CartItem> expectedCartItemList = cartItemRepository.findAll();

        //Then
        assertEquals(2, expectedCartItemList.size());

        //Cleanup with AfterEach
    }
}
