package com.Tavin.bookstore.dtos.Errors;

import org.springframework.http.HttpStatus;

import java.util.List;

public record ErrorResponse(int statusCode, String message, List<fieldErrors> errors) {

    public static ErrorResponse standardAnswer(String message) {
        return new ErrorResponse(HttpStatus.BAD_REQUEST.value(), message, List.of());
    }

    public static ErrorResponse errorConflict(String message) {
        return new ErrorResponse(HttpStatus.CONFLICT.value(), message, List.of());
    }
}
