package com.kodilla.ecommercee.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "CARTS")
public class Cart {
    @Id
    private long id;
}