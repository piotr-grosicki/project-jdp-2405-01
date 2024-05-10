package com.kodilla.ecommercee.controller;

import com.kodilla.ecommercee.dto.request.CreateUserRequest;
import com.kodilla.ecommercee.dto.request.UpdateUserRequest;
import com.kodilla.ecommercee.dto.response.UserResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("shop/v1/user")
public class UserController {

    @GetMapping
    public ResponseEntity<List<UserResponse>> getAllUsers() {
        return ResponseEntity.ok(new ArrayList<>());
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserResponse> getUser(@PathVariable Long id) {
        return ResponseEntity.ok(new UserResponse(id, "Marco", "pass123", "my address 12/3"));
    }

    @PostMapping
    public ResponseEntity<UserResponse> createUser(@RequestBody CreateUserRequest createUserRequest) {
        return ResponseEntity.ok(new UserResponse(123L, "Polo", "anotherPass321", "my address 24"));
    }

    @PutMapping
    public ResponseEntity<UserResponse> updateUser(@RequestBody UpdateUserRequest updateUserRequest) {
        return ResponseEntity.ok(new UserResponse(123L, "updated_Polo", "updatedAnotherPass321", "updated my address 24"));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable Long id) {
        return ResponseEntity.noContent().build();
    }

}
