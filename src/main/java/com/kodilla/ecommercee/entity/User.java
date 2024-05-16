package com.kodilla.ecommercee.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.List;

@Data
@Entity
@Table(name = "USERS")
@AllArgsConstructor
@NoArgsConstructor
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "USER_ID", nullable = false)
    private Long id;

    @Column(name = "USERNAME", nullable = false)
    private String username;

    @Column(name = "PASSWORD", nullable = false)
    private String password;

    @Column(name = "ADDRESS", nullable = false)
    private String address;

    @OneToMany(targetEntity = Order.class,
            mappedBy = "user",
            fetch = FetchType.EAGER)
    private List<Order> orders;
  
    @OneToMany(mappedBy = "user")
    private List<Cart> carts;
}