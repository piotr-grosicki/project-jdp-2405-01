package com.kodilla.ecommercee.exception;

public class NotEnoughPriceException extends RuntimeException {
    public NotEnoughPriceException() {
        super("Total price is not sufficient to cover the cart total price");
    }
}
