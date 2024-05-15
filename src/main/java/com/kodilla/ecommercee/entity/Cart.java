package com.kodilla.ecommercee.entity;

import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
@Table(name = "CARTS")
public class Cart {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private Long id;

    @OneToOne
    @JoinColumn(name = "USER_ID",nullable = false)
    private User user;

    @OneToMany(
            targetEntity = Product.class,
            mappedBy = "cart",
            cascade = CascadeType.ALL,
            fetch = FetchType.EAGER
    )
    private List<Product> productList = new ArrayList<>();

    @Column(name = "TOTAL_PRODUCT_PRICE")
    private BigDecimal totalProductPrice;

    @Column(name = "IS_ACTIVE")
    private Boolean isActive;
}