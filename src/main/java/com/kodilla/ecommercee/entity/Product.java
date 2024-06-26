package com.kodilla.ecommercee.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import java.math.BigDecimal;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "PRODUCTS")
public class Product {

    @ManyToOne
    @JoinColumn(name = "GROUP_ID")
    private Group group;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "PRODUCT_ID", unique = true, nullable = false)
    private Long id;

    @Column(name = "NAME", nullable = false)
    private String name;

    @Column(name = "DESCRIPTION")
    private String description;

    @Column(name = "PRICE")
    private BigDecimal price = BigDecimal.ZERO;

    @Column(name = "QUANTITY")
    private Integer quantity = 0;

    @OneToMany(mappedBy = "product")
    private List<CartItem> cartItems;

    public Product(String name, String description, BigDecimal price, Integer quantity) {
        this.name = name;
        this.description = description;
        this.price = price;
        this.quantity = quantity;
    }

    public Product(String name, String description, BigDecimal price, Integer quantity, Group group) {
        this.name = name;
        this.description = description;
        this.price = price;
        this.quantity = quantity;
        this.group = group;
    }
}
