package com.crp.ucp.exception;

import com.crp.ucp.account.authentication.BadCredentialsException;
import com.crp.ucp.account.exception.AccountNotFoundException;
import com.crp.ucp.server.model.ApiError;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

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
}
