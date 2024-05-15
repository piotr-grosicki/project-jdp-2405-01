package com.kodilla.ecommercee.repository;

import com.kodilla.ecommercee.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
}
