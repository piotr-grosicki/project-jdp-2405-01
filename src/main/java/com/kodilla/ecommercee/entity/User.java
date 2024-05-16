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
    @Column(name = "USER_ID")
    private Long id;

    @Column(name = "USERNAME")
    private String username;

    @Column(name = "PASSWORD")
    private String password;

    @Column(name = "ADDRESS")
    private String address;

    @OneToMany(targetEntity = Order.class,
            mappedBy = "user",
            fetch = FetchType.EAGER)
    private List<Order> orders;

    @OneToOne(mappedBy = "user")
    private Cart cart;

    public User(Long id) {
        this.id = id;
    }
}
