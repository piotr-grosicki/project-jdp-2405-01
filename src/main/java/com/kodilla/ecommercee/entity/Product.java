package com.kodilla.ecommercee.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.math.BigDecimal;

@Data
@NoArgsConstructor
@Entity
@Table(name = "PRODUCTS")
public class Product {

    @ManyToOne
    @JoinColumn(name = "GROUP_ID")
    private Group group;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "PRODUCT_ID", unique = true)
    private Long id;

    @Column(name = "NAME")
    private String name;

    @Column(name = "DESCRIPTION")
    private String description;

    @Column(name = "PRICE")
    private BigDecimal price;

    @Column(name = "Quantity")
    private Integer quantity;

    public Product(String name, String description, BigDecimal price, Integer quantity, Group group) {
        this.name = name;
        this.description = description;
        this.price = price;
        this.quantity = quantity;
        this.group = group;
    }
}
