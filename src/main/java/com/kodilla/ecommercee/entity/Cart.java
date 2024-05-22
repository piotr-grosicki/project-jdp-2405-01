package com.kodilla.ecommercee.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Data
@Entity
@Table(name = "CARTS")
@AllArgsConstructor
@NoArgsConstructor
public class Cart {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "CART_ID", nullable = false)
    private Long id;

    @OneToMany(mappedBy = "cart", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<CartItem> cartItems = new ArrayList<CartItem>();

    @ManyToOne
    @JoinColumn(name = "USER_ID")
    private User user;

    @Column(name = "TOTAL_PRODUCT_PRICE")
    private BigDecimal totalProductPrice;

    @Column(name = "IS_ACTIVE")
    private Boolean isActive;

    public Cart(List<CartItem> cartItems, User user, BigDecimal totalProductPrice, Boolean isActive) {
        this.cartItems = cartItems;
        this.user = user;
        this.totalProductPrice = totalProductPrice;
        this.isActive = isActive;
    }

    public Cart(User user) {
        this.user = user;
    }
}