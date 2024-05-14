package com.kodilla.ecommercee.repository;

import com.kodilla.ecommercee.entity.Cart;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
@Repository
@Transactional
public interface CartRepository extends JpaRepository<Cart, Long>{
}