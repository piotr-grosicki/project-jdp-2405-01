package com.kodilla.ecommercee.service;

import com.kodilla.ecommercee.dto.request.AddProductToCartRequest;
import com.kodilla.ecommercee.dto.request.CreateCartRequest;
import com.kodilla.ecommercee.dto.request.RemoveProductFromCartRequest;
import com.kodilla.ecommercee.dto.response.CartResponse;
import com.kodilla.ecommercee.dto.response.OrderResponse;
import com.kodilla.ecommercee.dto.response.ProductResponse;
import com.kodilla.ecommercee.entity.Cart;
import com.kodilla.ecommercee.entity.Order;
import com.kodilla.ecommercee.entity.Product;
import com.kodilla.ecommercee.entity.User;
import com.kodilla.ecommercee.exception.CartNotFoundException;
import com.kodilla.ecommercee.exception.ProductNotFoundException;
import com.kodilla.ecommercee.exception.UserNotFoundException;
import com.kodilla.ecommercee.mapper.CartMapper;
import com.kodilla.ecommercee.mapper.OrderMapper;
import com.kodilla.ecommercee.mapper.ProductMapper;
import com.kodilla.ecommercee.repository.CartRepository;
import com.kodilla.ecommercee.repository.OrderRepository;
import com.kodilla.ecommercee.repository.ProductRepository;
import com.kodilla.ecommercee.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CartService {
    private final CartRepository cartRepository;
    private final UserRepository userRepository;
    private final ProductRepository productRepository;
    private final OrderRepository orderRepository;

    private final CartMapper cartMapper;
    private final ProductMapper productMapper;
    private final OrderMapper orderMapper;

    public List<CartResponse> getAllCarts() {
        List<Cart> carts = cartRepository.findAll();
        return cartMapper.mapToCartListResponse(carts);
    }

    public CartResponse getCart(Long id) throws CartNotFoundException {
        Cart cart = cartRepository.findById(id).orElseThrow(() -> new CartNotFoundException(id));
        return cartMapper.mapToCartResponse(cart);
    }

    public CartResponse createEmptyCart(CreateCartRequest createCartRequest) throws UserNotFoundException {
        User user = userRepository.findById(createCartRequest.userId()).orElseThrow(() -> new UserNotFoundException(createCartRequest.userId()));
        Cart cart = new Cart(user);
        cartRepository.save(cart);

        return cartMapper.mapToCartResponse(cart);
    }

    public CartResponse addProductToCart(AddProductToCartRequest addProductToCartRequest) throws CartNotFoundException, ProductNotFoundException {
        Cart cart = cartRepository.findById(addProductToCartRequest.cartId()).orElseThrow(() -> new CartNotFoundException(addProductToCartRequest.cartId()));
        cart.getProducts().add(productRepository.findById(addProductToCartRequest.productId()).orElseThrow(() -> new ProductNotFoundException(addProductToCartRequest.productId())));

        cartRepository.save(cart);
        return cartMapper.mapToCartResponse(cart);
    }

    public ProductResponse deleteProductFromCart(RemoveProductFromCartRequest removeProductFromCartRequest) throws CartNotFoundException, ProductNotFoundException {
        Cart cart = cartRepository.findById(removeProductFromCartRequest.cartId()).orElseThrow(() -> new CartNotFoundException(removeProductFromCartRequest.cartId()));

        Product productToRemove = cart.getProducts().stream()
                .filter(p -> p.getId().equals(removeProductFromCartRequest.productId()))
                .findFirst()
                .orElseThrow(() -> new ProductNotFoundException(removeProductFromCartRequest.productId()));

        cart.getProducts().remove(productToRemove);
        cartRepository.save(cart);
        return productMapper.mapToProductResponse(productToRemove);
    }

    public OrderResponse createOrderFromCart(Long cartId) throws CartNotFoundException{
        Cart cart = cartRepository.findById(cartId).orElseThrow(() -> new CartNotFoundException(cartId));
        User user = cart.getUser();
        Order order = new Order(
                cart.getTotalProductPrice(),
                user.getAddress(),
                false,
                user,
                cart
        );

        orderRepository.save(order);
        return orderMapper.toOrderResponse(order);


    }


}
