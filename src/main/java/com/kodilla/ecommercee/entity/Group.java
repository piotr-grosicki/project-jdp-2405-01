package com.kodilla.ecommercee.entity;


import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "PRODUCT_GROUPS")
public class Group {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name ="GROUP_ID")
    private Long id;
}
