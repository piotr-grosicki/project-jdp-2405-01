package com.kodilla.ecommercee.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Entity(name = "CARTS")
public class Cart {

    @Id
    @GeneratedValue
    private Long id;

    @Setter
    @OneToOne
    @JoinColumn(name = "USER_ID",nullable = false)
    private User user;

    @Setter
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