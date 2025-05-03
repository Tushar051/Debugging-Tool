package com.dubbgingSystem.debugger.exception;


import org.springframework.validation.BindingResult;

import java.util.stream.Collectors;

public class InvalidRequestException extends RuntimeException {
    public InvalidRequestException(BindingResult bindingResult){
        super(bindingResult.getFieldErrors().stream()
                .map(fe -> fe.getField() + ": "+ fe.getDefaultMessage())
                .collect(Collectors.joining(": ")));
    }
}
