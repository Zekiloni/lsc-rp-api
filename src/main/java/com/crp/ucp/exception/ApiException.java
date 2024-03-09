package com.crp.ucp.exception;

import com.crp.ucp.server.model.ApiError;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.NoSuchElementException;

@ControllerAdvice
public class ApiException {

    @ExceptionHandler(NoSuchElementException.class)
    public ResponseEntity<ApiError> handleNoSuchElement(NoSuchElementException exception) {

        ApiError error = new ApiError();
        error.setStatus(HttpStatus.NOT_FOUND.toString());
        error.setMessage(exception.getMessage());

        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(error);
    }
}
