package com.Tavin.bookstore.exceptions;

public class operationNotPermitted extends RuntimeException {
    public operationNotPermitted(String message) {
        super(message);
    }
}
