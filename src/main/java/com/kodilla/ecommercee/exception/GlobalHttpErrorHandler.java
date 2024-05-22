package com.kodilla.ecommercee.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class GlobalHttpErrorHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(InvalidCredentialsException.class)
    public ResponseEntity<Object> handleInvalidCredentialsException(InvalidCredentialsException invalidCredentialsException) {
        return new ResponseEntity<>(invalidCredentialsException.getMessage(), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(NullOrEmptyValueException.class)
    public ResponseEntity<Object> handleNullValueException(NullOrEmptyValueException nullOrEmptyValueException) {
        return new ResponseEntity<>(nullOrEmptyValueException.getMessage(), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(UsernameAlreadyExistsException.class)
    public ResponseEntity<Object> handleUsernameAlreadyExistsException(UsernameAlreadyExistsException usernameAlreadyExistsException) {
        return new ResponseEntity<>(usernameAlreadyExistsException.getMessage(), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(UserNotFoundException.class)
    public ResponseEntity<Object> handlesUserNotFoundException(UserNotFoundException userNotFoundException) {
        return new ResponseEntity<>(userNotFoundException.getMessage(), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(UsernameNotFoundException.class)
    public ResponseEntity<Object> handleUsernameNotFoundException(UsernameNotFoundException usernameNotFoundException) {
        return new ResponseEntity<>(usernameNotFoundException.getMessage(), HttpStatus.BAD_REQUEST);
    }

}
