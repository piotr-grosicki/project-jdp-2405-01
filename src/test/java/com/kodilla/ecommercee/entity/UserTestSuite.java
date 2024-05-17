package com.kodilla.ecommercee.entity;

import com.kodilla.ecommercee.repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class UserTestSuite {

    @Autowired
    private UserRepository userRepository;

    @Test
    void shouldGetAllUsers() {
        //given
        User user1 = new User("test", "test", "test");
        User user2 = new User("test", "test", "test");
        User user3 = new User("test", "test", "test");
        userRepository.save(user1);
        userRepository.save(user2);
        userRepository.save(user3);

        //when
        List<User> users = userRepository.findAll();

        //then
        assertEquals(3, users.size());

        //clean up
        userRepository.delete(user1);
        userRepository.delete(user2);
        userRepository.delete(user3);
    }

    @Test
    void shouldGetUserById() {
        //given
        User user1 = new User("test", "test", "test");
        User user2 = new User("test", "test", "test");
        userRepository.save(user1);
        userRepository.save(user2);

        //when
        Optional<User> expectedUser = userRepository.findById(user1.getId());

        //then
        assertThat(expectedUser).isNotNull();
        assertEquals(expectedUser.get().getId(), user1.getId());
        assertNotEquals(expectedUser.get().getId(), user2.getId());

        //clean up
        userRepository.delete(user1);
        userRepository.delete(user2);
    }

    @Test
    void shouldDeleteUser() {
        //given
        User user1 = new User("test", "test", "test");
        User user2 = new User("test", "test", "test");
        User user3 = new User("test", "test", "test");
        userRepository.save(user1);
        userRepository.save(user2);
        userRepository.save(user3);

        //when
        userRepository.deleteById(user2.getId());
        Optional<User> expectedUser = userRepository.findById(user2.getId());

        //then
        assertEquals(Optional.empty(), expectedUser);

        //clean up
        userRepository.delete(user1);
        userRepository.delete(user3);
    }

    @Test
    void shouldCreateUser() {
        //given
        User user1 = new User("New username", "test", "test");

        //when
        userRepository.save(user1);
        Optional<User> expectedUser = userRepository.findById(user1.getId());

        //then
        assertNotNull(user1);
        assertEquals("New username", expectedUser.get().getUsername());

        //clean up
        userRepository.delete(user1);
    }

    @Test
    void shouldUpdateUser() {
        //given
        User user1 = new User("New username", "test", "test");
        userRepository.save(user1);

        //when
        user1.setPassword("Updated password");
        userRepository.save(user1);
        Optional<User> expectedUser = userRepository.findById(user1.getId());

        //then
        assertEquals("Updated password", expectedUser.get().getPassword());

        //clean up
        userRepository.delete(user1);
    }


}
