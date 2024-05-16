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
    @Column(name = "CART_ID")
    private Long id;

    @ManyToMany
    @JoinTable(
            name = "JOIN_PRODUCT_GROUP",
            joinColumns = {@JoinColumn(name = "CART_ID", referencedColumnName = "CART_ID")},
            inverseJoinColumns = {@JoinColumn(name = "PRODUCT_ID", referencedColumnName = "PRODUCT_ID")}
    )
    private List<Product> products = new ArrayList<>();

    @ManyToOne
    @JoinColumn(name = "USER_ID", nullable = false)
    private User user;

    @Column(name = "TOTAL_PRODUCT_PRICE", nullable = false)
    private BigDecimal totalProductPrice;

    @Column(name = "IS_ACTIVE", nullable = false)
    private Boolean isActive;

    public Cart(Long id, User user) {
        this.id = id;
        this.user = user;
    }

    public Cart(List<Product> products, User user, BigDecimal totalProductPrice, Boolean isActive) {
        this.products = products;
        this.user = user;
        this.totalProductPrice = totalProductPrice;
        this.isActive = isActive;
    }
}