package com.kodilla.ecommercee.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "CARTS")
public class Cart {
    @Id
    private long id;
    @OneToOne
    @JoinColumn(name = "USERS_ID", nullable = false)
    private User user;
}