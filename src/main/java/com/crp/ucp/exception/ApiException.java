package com.crp.ucp.exception;

import com.crp.ucp.account.authentication.BadCredentialsException;
import com.crp.ucp.account.exception.AccountNotFoundException;
import com.crp.ucp.server.model.ApiError;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

import java.sql.SQLIntegrityConstraintViolationException;

import static java.lang.String.format;

@ControllerAdvice
public class ApiException {

    @ExceptionHandler(AccountNotFoundException.class)
    public ResponseEntity<ApiError> handleAccountNotFound(AccountNotFoundException exception) {
        ApiError error = new ApiError();
        error.setStatus(HttpStatus.NOT_FOUND.toString());
        error.setMessage(exception.getMessage());

        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(error);
    }

    @ExceptionHandler(BadCredentialsException.class)
    public ResponseEntity<ApiError> handleWrongPassword(BadCredentialsException exception) {
        ApiError error = new ApiError();
        error.setStatus(HttpStatus.UNAUTHORIZED.toString());
        error.setMessage(exception.getMessage());

        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(error);
    }

    @ExceptionHandler(SQLIntegrityConstraintViolationException.class)
    public ResponseEntity<ApiError> handleIntegrityConstraintViolation(
            SQLIntegrityConstraintViolationException exception) {

        ApiError error = new ApiError();
        error.setStatus(HttpStatus.BAD_REQUEST.toString());
        error.setMessage(exception.getLocalizedMessage());

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(error);
    }

    @ExceptionHandler(MethodArgumentTypeMismatchException.class)
    public ResponseEntity<ApiError> handleMethodArgumentTypeMismatchException(
            MethodArgumentTypeMismatchException exception) {

        ApiError error = new ApiError();
        error.setStatus(HttpStatus.BAD_REQUEST.toString());
        error.setReason(exception.getLocalizedMessage());
        error.setMessage(format("Failed to convert value: %s", exception.getValue()));

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(error);
    }
}
