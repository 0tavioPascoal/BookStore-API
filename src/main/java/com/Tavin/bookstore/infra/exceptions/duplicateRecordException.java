package com.Tavin.bookstore.infra.exceptions;

public class duplicateRecordException extends RuntimeException {
    public duplicateRecordException(String message) {
        super(message);
    }
}
