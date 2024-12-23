package com.Tavin.bookstore.infra.handlers;

import com.Tavin.bookstore.infra.errors.ErrorResponse;
import com.Tavin.bookstore.infra.errors.FieldErrors;
import com.Tavin.bookstore.infra.exceptions.DuplicateRecordException;
import com.Tavin.bookstore.infra.exceptions.OperationNotPermitted;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import jakarta.servlet.ServletException;
import org.springframework.http.HttpStatus;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.io.IOException;
import java.nio.file.AccessDeniedException;
import java.util.List;
import java.util.stream.Collectors;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.UNPROCESSABLE_ENTITY)
    public ErrorResponse handlerErrorResponse(MethodArgumentNotValidException exception) {
        List<FieldError> fieldErrors = exception.getFieldErrors();
        List<FieldErrors> errorCollect = fieldErrors.stream().map(
                        fe -> new FieldErrors(fe.getField(), fe.getDefaultMessage()))
                .collect(Collectors.toList());
        return new ErrorResponse(HttpStatus.UNPROCESSABLE_ENTITY.value(),
                "Validation Error",
                errorCollect
        );
    }

    @ExceptionHandler(AccessDeniedException.class)
    @ResponseStatus(HttpStatus.FORBIDDEN)
    public ErrorResponse handleAccesDeniedException(AccessDeniedException e){
        return new ErrorResponse(HttpStatus.FORBIDDEN.value(), "Acesso Negado.", List.of());
    }

    @ExceptionHandler(DuplicateRecordException.class)
    @ResponseStatus(HttpStatus.CONFLICT)
    public ErrorResponse handleDuplicatedRecordException(DuplicateRecordException e) {
        return ErrorResponse.errorConflict(e.getMessage());

    }

    @ExceptionHandler(OperationNotPermitted.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErrorResponse handleOperationNotPermitted(OperationNotPermitted e) {
        return ErrorResponse.standardAnswer(e.getMessage());
    }

    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ErrorResponse handleUntreatedErrors(RuntimeException e) {
        return new ErrorResponse(HttpStatus.INTERNAL_SERVER_ERROR.value(),
                "An unexpected error has occurred, please contact the administration",
                List.of());
    }

    @ExceptionHandler(JWTCreationException.class)
    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    public ErrorResponse handleJwtCreate(JWTCreationException e) {
        return new ErrorResponse(HttpStatus.UNAUTHORIZED.value(), "JWT Creation Error", List.of());
    }

    @ExceptionHandler(JWTVerificationException.class)
    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    public ErrorResponse handleJWTVerificationException(JWTVerificationException e) {
        return new ErrorResponse(HttpStatus.UNAUTHORIZED.value(), "JWT Verification Error", List.of());
    }

    @ExceptionHandler(ServletException.class)
    public ErrorResponse handleServletExecptionError(ServletException e) {
        return new ErrorResponse(HttpStatus.INTERNAL_SERVER_ERROR.value(), "Servlet Error", List.of());
    }

    @ExceptionHandler(IOException.class )
    public ErrorResponse handleIoException(IOException e) {
        return new ErrorResponse(HttpStatus.INTERNAL_SERVER_ERROR.value(), "IO Error", List.of());
    }
}
