package com.kodilla.ecommercee.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class GlobalHttpErrorHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(GroupHasProductsException.class)
    public ResponseEntity<Object> handleGroupHasProductsException(GroupHasProductsException groupHasProductsException) {
        return new ResponseEntity<>("Group cannot be deleted. Group still have products.", HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(GroupNotFoundException.class)
    public ResponseEntity<Object> handleGroupNotFoundException(GroupNotFoundException groupNotFoundException) {
        return new ResponseEntity<>("Group with given id not found.", HttpStatus.BAD_REQUEST);
    }
}
