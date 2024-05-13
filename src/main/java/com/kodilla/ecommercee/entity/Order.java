package com.kodilla.ecommercee.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "ORDERS")
public class Order {
    @Id
    private Long id;

    @ManyToOne
    @JoinColumn(name = "USERS_ID", nullable = false)
    private User user;
}
