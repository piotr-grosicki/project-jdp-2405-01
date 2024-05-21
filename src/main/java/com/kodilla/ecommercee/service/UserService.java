package com.kodilla.ecommercee.service;

import com.kodilla.ecommercee.dto.request.CreateUserRequest;
import com.kodilla.ecommercee.dto.request.LockUserRequest;
import com.kodilla.ecommercee.dto.request.UpdateUserRequest;
import com.kodilla.ecommercee.dto.request.UserCredentialsRequest;
import com.kodilla.ecommercee.dto.response.UserLockedResponse;
import com.kodilla.ecommercee.dto.response.UserResponse;
import com.kodilla.ecommercee.entity.User;
import com.kodilla.ecommercee.exception.InvalidCredentialsException;
import com.kodilla.ecommercee.exception.NullValueException;
import com.kodilla.ecommercee.exception.UserNotFoundException;
import com.kodilla.ecommercee.exception.UsernameAlreadyExistsException;
import com.kodilla.ecommercee.mapper.UserMapper;
import com.kodilla.ecommercee.repository.UserRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.security.SecureRandom;
import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final UserMapper userMapper;

    public List<UserResponse> getAllUsers() {
        List<User> users = userRepository.findAll();

        return userMapper.mapToUsersList(users);
    }

    public UserResponse getUser(Long userId) throws UserNotFoundException {
        User user = userRepository.findById(userId).orElseThrow(() -> new UserNotFoundException(String.format("User with id: %s not found", userId)));

        return userMapper.toUserResponse(user);
    }

    public UserResponse createUser(CreateUserRequest createUserRequest) throws NullValueException, UsernameAlreadyExistsException {
        if (userRepository.findUserByUsername(createUserRequest.username()).isPresent()) {
            throw new UsernameAlreadyExistsException(String.format("User with username: %s already exists", createUserRequest.username()));
        }

        if (createUserRequest.username() == null || createUserRequest.password() == null || createUserRequest.address() == null) {
            throw new NullValueException("username, password or address is null");
        }
        User newUser = new User(createUserRequest.username(), createUserRequest.password(), createUserRequest.address());
        newUser.setUserLocked(false);

        userRepository.save(newUser);

        return userMapper.toUserResponse(newUser);
    }

    public UserResponse updateUser(UpdateUserRequest updateUserRequest) throws UserNotFoundException, UsernameAlreadyExistsException {
        User user = userRepository.findById(updateUserRequest.id()).orElseThrow(() -> new UserNotFoundException(String.format("User with id: %s not found", updateUserRequest.id())));
        boolean isUpdated = false;

        if (
                updateUserRequest.username() != null &&
                !updateUserRequest.username().isEmpty() &&
                !updateUserRequest.username().equals(user.getUsername()) &&
                !userRepository.findUserByUsername(updateUserRequest.username()).isPresent()
        ) {
            user.setUsername(updateUserRequest.username());
            isUpdated = true;
        } else {
            throw new UsernameAlreadyExistsException(String.format("User with username: %s already exists", updateUserRequest.username()));
        }

        if (updateUserRequest.password() != null && !updateUserRequest.password().isEmpty() && !updateUserRequest.password().equals(user.getPassword())) {
            user.setPassword(updateUserRequest.password());
            isUpdated = true;
        }

        if (updateUserRequest.address() != null && !updateUserRequest.address().isEmpty() && !updateUserRequest.address().equals(user.getAddress())) {
            user.setAddress(updateUserRequest.address());
            isUpdated = true;
        }

        if (isUpdated) {
            userRepository.save(user);
        }

        return userMapper.toUserResponse(user);
    }

    public void deleteUser(Long id) throws UserNotFoundException {
        User user = userRepository.findById(id).orElseThrow(() -> new UserNotFoundException(String.format("User with id: %s not found", id)));

        userRepository.delete(user);
    }

    public UserLockedResponse lockUser(LockUserRequest lockUserRequest) throws UserNotFoundException {
        User user = userRepository.findById(lockUserRequest.userId()).orElseThrow(() -> new UserNotFoundException(String.format("User with id: %s not found", lockUserRequest)));

        if (lockUserRequest.username() != null && !lockUserRequest.username().isEmpty() && user.getUsername().equals(lockUserRequest.username()) &&
                lockUserRequest.password() != null && !lockUserRequest.password().isEmpty() && user.getPassword().equals(lockUserRequest.password())
        ) {
            user.setUserLocked(true);
        }

        userRepository.save(user);

        return userMapper.mapToUserLockedResponse(user);
    }

    public Integer loginUser(UserCredentialsRequest userCredentialsRequest) throws UserNotFoundException, InvalidCredentialsException {

        User user = userRepository.findUserByUsername(userCredentialsRequest.username()).orElseThrow(() -> new UserNotFoundException(String.format("User with username: %s not found", userCredentialsRequest.username())));
        SecureRandom random = new SecureRandom();
        int min = 1000000;
        int max = 2000000;
        int token = random.nextInt(max - min) + min;

        if (userCredentialsRequest.password() != null && !userCredentialsRequest.password().isEmpty() && user.getPassword().equals(userCredentialsRequest.password())) {
            user.setToken(token);
            user.setTokenCreationTime(LocalDateTime.now());
            userRepository.save(user);
        } else {
            throw new InvalidCredentialsException("Invalid credentials");
        }
        return token;
    }

    @Transactional
    @Scheduled(fixedRate = 60000)
    public void cleanUpExpiredTokens() {
        LocalDateTime currentTime = LocalDateTime.now();
        List<User> users = userRepository.findAll();

        for (User user : users) {
            if (user.getTokenCreationTime() != null && user.getTokenCreationTime().plusHours(1).isBefore(currentTime)) {
                user.setToken(null);
                user.setTokenCreationTime(null);
                userRepository.save(user);
            }
        }

    }
}
