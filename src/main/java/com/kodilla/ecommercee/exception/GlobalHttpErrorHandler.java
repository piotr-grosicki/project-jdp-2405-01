package com.kodilla.ecommercee.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class GlobalHttpErrorHandler extends ResponseEntityExceptionHandler {
    @ExceptionHandler(ProductNotFoundException.class)
    public ResponseEntity<Object> handleProductException(ProductNotFoundException productNotFoundException) {
        return new ResponseEntity<>("Product with given Id doesn't exist", HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(GroupHasProductsException.class)
    public ResponseEntity<Object> handleGroupHasProductsException(GroupHasProductsException groupHasProductsException) {
        return new ResponseEntity<>("Group with given id still has products in it and cannot be deleted", HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(GroupNotFoundException.class)
    public ResponseEntity<Object> handleBookCopyException(GroupNotFoundException groupNotFoundException) {
        return new ResponseEntity<>("Group with given id could not be found", HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(NullValueException.class)
    public ResponseEntity<Object> handleNullValueException(NullValueException nullValueException){
        return new ResponseEntity<>("Null value is not allowed",HttpStatus.BAD_REQUEST);
    }

}