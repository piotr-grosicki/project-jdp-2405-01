package com.kodilla.ecommercee.repository;

import com.kodilla.ecommercee.entity.Cart;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CartRepository extends JpaRepository<Cart, Long> {
}
