package com.dubbgingSystem.debugger.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

@ControllerAdvice
public class GlobalExceptionHandler {

    // Handles validation errors (@Valid)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorResponse> handleValidationExceptions(
            MethodArgumentNotValidException ex) {

        Map<String, String> errors = new HashMap<>();
        ex.getBindingResult().getAllErrors().forEach(error -> {
            String fieldName = ((FieldError) error).getField();
            String errorMessage = error.getDefaultMessage();
            errors.put(fieldName, errorMessage);
        });

        ErrorResponse response = new ErrorResponse(
                HttpStatus.BAD_REQUEST.value(),
                "Validation failed for " + errors.size() + " field(s)",
                LocalDateTime.now()
        );
        return ResponseEntity.badRequest().body(response);
    }

    // Handles custom 404 errors
    @ExceptionHandler(ErrorLogNotFoundException.class)
    public ResponseEntity<ErrorResponse> handleResourceNotFound(
            ErrorLogNotFoundException ex,
            WebRequest request) {

        ErrorResponse response = new ErrorResponse(
                HttpStatus.NOT_FOUND.value(),
                ex.getMessage(),
                LocalDateTime.now(),
                Map.of("path", request.getDescription(false))
        );
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
    }

    // Handles all other exceptions
    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorResponse> handleGlobalExceptions(
            Exception ex,
            WebRequest request) {

        ErrorResponse response = new ErrorResponse(
                HttpStatus.INTERNAL_SERVER_ERROR.value(),
                "An unexpected error occurred: " + ex.getMessage(),
                LocalDateTime.now(),
                Map.of("path", request.getDescription(false))
        );
        return ResponseEntity.internalServerError().body(response);
    }

    // Error response DTO
    public record ErrorResponse(
            int status,
            String message,
            LocalDateTime timestamp,
            Map<String, Object> details
    ) {
        // Compact constructor for default details
        public ErrorResponse(int status, String message, LocalDateTime timestamp) {
            this(status, message, timestamp, null);
        }


    }
}