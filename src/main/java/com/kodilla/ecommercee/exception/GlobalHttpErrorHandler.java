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
        return new ResponseEntity<>("Invalid credentials.", HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(NullValueException.class)
    public ResponseEntity<Object> handleNullValueException(NullValueException nullValueException) {
        return new ResponseEntity<>("Username, password or address is null.", HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(UsernameAlreadyExistsException.class)
    public ResponseEntity<Object> handleUsernameAlreadyExistsException(UsernameAlreadyExistsException usernameAlreadyExistsException) {
        return new ResponseEntity<>("Username is already taken.", HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(UserNotFoundException.class)
    public ResponseEntity<Object> handlesUserNotFoundException(UserNotFoundException userNotFoundException) {
        return new ResponseEntity<>("User could not be found.", HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(UsernameNotFoundException.class)
    public ResponseEntity<Object> handleUsernameNotFoundException(UsernameNotFoundException usernameNotFoundException) {
        return new ResponseEntity<>("Username could not be found", HttpStatus.BAD_REQUEST);
    }

}
