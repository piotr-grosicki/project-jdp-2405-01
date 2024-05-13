package com.kodilla.ecommercee.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Entity(name = "Carts")
public class Cart {

    @Id
    @GeneratedValue
    private Long id;

/*    @Setter
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
    private List<Product>productList = new ArrayList<>();*/


    @Column(name = "TOTAL_PRODUCT_PRICE")
    private BigDecimal totalProductPrice;

    @Column(name = "IS_ACTIVE")
    private Boolean isActive;
}

