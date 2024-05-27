package com.kodilla.ecommercee.exception;

public class NegativeValuesException extends Exception {
    public NegativeValuesException() {
        super("Negative value is not allowed.");
    }
}
