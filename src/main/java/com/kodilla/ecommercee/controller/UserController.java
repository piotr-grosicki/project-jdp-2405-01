package com.kodilla.ecommercee.controller;

import com.kodilla.ecommercee.dto.request.CreateUserRequest;
import com.kodilla.ecommercee.dto.request.LockUserRequest;
import com.kodilla.ecommercee.dto.request.UserCredentialsRequest;
import com.kodilla.ecommercee.dto.request.UpdateUserRequest;
import com.kodilla.ecommercee.dto.response.UserResponse;
import com.kodilla.ecommercee.dto.response.UserLockedResponse;
import com.kodilla.ecommercee.exception.InvalidCredentialsException;
import com.kodilla.ecommercee.exception.UserNotFoundException;
import com.kodilla.ecommercee.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("shop/v1/user")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @GetMapping
    public ResponseEntity<List<UserResponse>> getAllUsers() {
        return ResponseEntity.ok(userService.getAllUsers());
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserResponse> getUser(@PathVariable Long id) throws UserNotFoundException {
        return ResponseEntity.ok(userService.getUser(id));
    }

    @PostMapping
    public ResponseEntity<UserResponse> createUser(@RequestBody CreateUserRequest createUserRequest) {
        return ResponseEntity.ok(userService.createUser(createUserRequest));
    }

    @PostMapping("/login")
    public Integer loginUser(@RequestBody UserCredentialsRequest userCredentialsRequest) throws UserNotFoundException, InvalidCredentialsException {
        return userService.loginUser(userCredentialsRequest);
    }

    @PutMapping
    public ResponseEntity<UserResponse> updateUser(@RequestBody UpdateUserRequest updateUserRequest) throws UserNotFoundException {
        return ResponseEntity.ok(userService.updateUser(updateUserRequest));
    }

    @PutMapping("/lock")
    public ResponseEntity<UserLockedResponse> lockUser(@RequestBody LockUserRequest lockUserRequest) throws UserNotFoundException {
        return ResponseEntity.ok(userService.lockUser(lockUserRequest));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable Long id) throws UserNotFoundException {
        userService.deleteUser(id);
        return ResponseEntity.noContent().build();
    }

}
