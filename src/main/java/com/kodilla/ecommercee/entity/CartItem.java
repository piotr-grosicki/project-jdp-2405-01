package com.kodilla.ecommercee.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@Table(name = "CART_ITEMS")
@AllArgsConstructor
@NoArgsConstructor
public class CartItem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "CART_ITEM_ID", nullable = false)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "PRODUCT_ID", nullable = false)
    private Product product;

    @Column(name = "QUANTITY", nullable = false)
    private Integer quantity = 0;

    @ManyToOne
    @JoinColumn(name = "CART_ID", nullable = false)
    private Cart cart;

    public CartItem(Product product, Integer quantity, Cart cart) {
        this.product = product;
        this.quantity = quantity;
        this.cart = cart;
    }
}
