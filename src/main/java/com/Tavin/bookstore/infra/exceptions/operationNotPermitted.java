package com.Tavin.bookstore.infra.exceptions;

public class operationNotPermitted extends RuntimeException {
    public operationNotPermitted(String message) {
        super(message);
    }
}
