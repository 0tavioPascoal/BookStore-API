package com.Tavin.bookstore.infra.exceptions;

public class OperationNotPermitted extends RuntimeException {
    public OperationNotPermitted(String message) {
        super(message);
    }
}
