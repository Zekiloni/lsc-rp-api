package net.lscrp.ucp.exception;

import net.lscrp.ucp.account.authentication.BadCredentialsException;
import net.lscrp.ucp.account.AccountException;
import net.lscrp.ucp.account.password.reset.ResetPasswordTokenException;
import net.lscrp.ucp.server.model.ApiError;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.MalformedJwtException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

import java.sql.SQLIntegrityConstraintViolationException;
import java.util.NoSuchElementException;

import static java.lang.String.format;

@ControllerAdvice
public class ApiExceptionHandler {


    public static final String CHARACTERS_NAME = "characters.name";
    public static final String ACCOUNTS_USERNAME = "accounts.username";

    @ExceptionHandler(AccountException.class)
    public ResponseEntity<ApiError> handleAccountNotFound(AccountException exception) {
        ApiError error = new ApiError();
        error.setStatus(HttpStatus.NOT_FOUND.toString());
        error.setMessage(exception.getMessage());

        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(error);
    }

    @ExceptionHandler(NoSuchElementException.class)
    public ResponseEntity<ApiError> handleAccountNotFound(NoSuchElementException exception) {
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

        String exceptionMessage = exception.getMessage();

        // Check for duplicate entry in 'characters.name'
        if (exceptionMessage.contains(CHARACTERS_NAME)) {
            String characterName = extractValueFromDuplicateEntryValueException(exceptionMessage);
            error.setMessage(String.format("Ime karaktera '%s' je već zauzeto.", characterName));
        }
        // Check for duplicate entry in 'accounts.username'
        else if (exceptionMessage.contains(ACCOUNTS_USERNAME)) {
            String username = extractValueFromDuplicateEntryValueException(exceptionMessage);
            error.setMessage(String.format("Username '%s' je već zauzet.", username));
        } else {
            error.setMessage(exception.getLocalizedMessage());
        }

        error.setReason(exception.getLocalizedMessage());

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

    @ExceptionHandler(ExpiredJwtException.class)
    public ResponseEntity<ApiError> handleJwtExpiredException(ExpiredJwtException exception) {
        ApiError error = new ApiError();
        error.setStatus(HttpStatus.BAD_REQUEST.toString());
        error.setReason(exception.getLocalizedMessage());
        error.setMessage("Sesija je istekla.");

        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(error);
    }

    @ExceptionHandler(MalformedJwtException.class)
    public ResponseEntity<ApiError> handleMalformedJwtException(MalformedJwtException exception) {
        ApiError error = new ApiError();
        error.setStatus(HttpStatus.BAD_REQUEST.toString());
        error.setReason(exception.getLocalizedMessage());
        error.setMessage("Došlo je do greške prilikom verifikacije vaše sesije.");

        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(error);
    }

    @ExceptionHandler(ResetPasswordTokenException.class)
    public ResponseEntity<ApiError> handleResetPasswordTokenException(ResetPasswordTokenException exception) {
        ApiError error = new ApiError();
        error.setStatus(HttpStatus.FORBIDDEN.toString());
        error.setReason(exception.getLocalizedMessage());
        error.setMessage(exception.getMessage());

        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(error);
    }

    private String extractValueFromDuplicateEntryValueException(String message) {
        // Assuming the message format: "Duplicate entry 'value' for key 'some_key'"
        String[] parts = message.split("'");
        return parts.length > 1 ? parts[1] : "unknown";
    }
}
