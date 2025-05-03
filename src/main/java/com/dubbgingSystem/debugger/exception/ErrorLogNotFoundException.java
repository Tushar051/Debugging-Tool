package com.dubbgingSystem.debugger.exception;

public class ErrorLogNotFoundException extends RuntimeException{
    public ErrorLogNotFoundException(String id) {
        super("Error log not found with ID: " + id);
    }
}
