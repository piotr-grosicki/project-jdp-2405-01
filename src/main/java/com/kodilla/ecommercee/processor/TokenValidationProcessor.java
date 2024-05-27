package com.kodilla.ecommercee.processor;

import com.kodilla.ecommercee.entity.User;
import com.kodilla.ecommercee.exception.InvalidTokenException;
import com.kodilla.ecommercee.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
@RequiredArgsConstructor
@Aspect
public class TokenValidationProcessor {

    private final UserRepository userRepository;

    @Before("execution(* com.kodilla.ecommercee.controller.CartController.*(..)) && args(..,token) || " +
            "execution(* com.kodilla.ecommercee.controller.UserController.*(..)) && args(..,token) || " +
            "execution(* com.kodilla.ecommercee.controller.GroupController.*(..)) && args(..,token) || " +
            "execution(* com.kodilla.ecommercee.controller.OrderController.*(..)) && args(..,token) || " +
            "execution(* com.kodilla.ecommercee.controller.ProductController.*(..)) && args(..,token)"
    )
    public void validateToken(Integer token) throws InvalidTokenException {
        Optional<User> userOptional = userRepository.findUsersByToken(token);

        if (userOptional.isEmpty() || userOptional.get().getToken() == null) {
            throw new InvalidTokenException();
        }
    }

}
