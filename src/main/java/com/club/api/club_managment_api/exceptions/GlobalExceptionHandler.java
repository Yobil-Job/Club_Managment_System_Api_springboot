package com.club.api.club_managment_api.exceptions;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.club.api.club_managment_api.exceptions.validations.NotValidException;

@RestControllerAdvice
public class GlobalExceptionHandler {

    
    @ExceptionHandler(resourceNotFoundException.class)
    public ResponseEntity<Map<String, Object>> handleResourceNotFound(resourceNotFoundException ex) {
        return buildResponse(HttpStatus.NOT_FOUND, ex.getMessage());
    }

    // 409 - Duplicate resource
    @ExceptionHandler(DuplicateResourceException.class)
    public ResponseEntity<Map<String, Object>> handleDuplicateResource(DuplicateResourceException ex) {
        return buildResponse(HttpStatus.CONFLICT, ex.getMessage());
    }

    // 400 - Field mismatch
    @ExceptionHandler(FieldMismatchException.class)
    public ResponseEntity<Map<String, Object>> handleFieldMismatch(FieldMismatchException ex) {
        return buildResponse(HttpStatus.BAD_REQUEST, ex.getMessage());
    }

    // 403 - Not authorized
    @ExceptionHandler(notAuthorizedUserException.class)
    public ResponseEntity<Map<String, Object>> handleNotAuthorized(notAuthorizedUserException ex) {
        return buildResponse(HttpStatus.FORBIDDEN, ex.getMessage());
    }

    // 409 - Already exists 
    @ExceptionHandler(ResourceAlreadyFoundException.class)
    public ResponseEntity<Map<String, Object>> handleAlreadyExists(ResourceAlreadyFoundException ex) {
        return buildResponse(HttpStatus.CONFLICT, ex.getMessage());
    }

    // 400 - Validation errors from @Valid
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Map<String, Object>> handleValidationException(MethodArgumentNotValidException ex) {
        String message = ex.getBindingResult().getFieldErrors().stream()
                           .map(error -> error.getField() + ": " + error.getDefaultMessage())
                           .reduce("", (msg1, msg2) -> msg1 + ", " + msg2);
        return buildResponse(HttpStatus.BAD_REQUEST, message);
    }

    // 400 - Not valid custom
    @ExceptionHandler(NotValidException.class)
    public ResponseEntity<Map<String, Object>> handleNotValid(NotValidException ex) {
        return buildResponse(HttpStatus.BAD_REQUEST, ex.getMessage());
    }

    // 500 - Fallback for any uncaught exception
    @ExceptionHandler(Exception.class)
    public ResponseEntity<Map<String, Object>> handleGenericException(Exception ex) {
        return buildResponse(HttpStatus.INTERNAL_SERVER_ERROR, ex.getMessage());
    }

    // Utility method to build JSON response
    private ResponseEntity<Map<String, Object>> buildResponse(HttpStatus status, String message) {
        Map<String, Object> body = new HashMap<>();
        body.put("status", status.value());
        body.put("error", status.getReasonPhrase());
        body.put("message", message);
        body.put("timestamp", LocalDateTime.now());
        return new ResponseEntity<>(body, status);
    }
}
