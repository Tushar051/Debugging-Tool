package com.dubbgingSystem.debugger.controller;

import com.dubbgingSystem.debugger.entity.ErrorLog;
import com.dubbgingSystem.debugger.enums.ErrorSeverity;
import com.dubbgingSystem.debugger.exception.ErrorLogNotFoundException;
import com.dubbgingSystem.debugger.exception.InvalidRequestException;
import com.dubbgingSystem.debugger.service.ErrorLogService;
import com.sun.jdi.request.InvalidRequestStateException;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/api/errors")
@Tag(name = "Error Lag", description = "API for error logging and management")
public class ErrorLogController {


    @Autowired
    private ErrorLogService errorLogService;


    @PostMapping
    @Operation(
            summary = "Log a new error",
            responses = {
                    @ApiResponse(responseCode = "201", description = "Error logged successfully"),
                    @ApiResponse(responseCode = "400", description = "Invalid input data")
            }
    )
    public ResponseEntity<ErrorLog> createErrorLog(
            @io.swagger.v3.oas.annotations.parameters.RequestBody(
                    description = "Error log details",
                    required = true
            )
            @Valid @RequestBody ErrorLog errorLog,
            BindingResult bindingResult) {

        if (bindingResult.hasErrors()){
            throw new InvalidRequestException(bindingResult);
        }
        ErrorLog savedError = errorLogService.createErrorLog(errorLog);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedError);
    }

    // Get all error logs
    @GetMapping
    @Operation(summary = "Get all errors logs")
    public ResponseEntity<List<ErrorLog>> getAllErrorLogs() {
        return ResponseEntity.ok(errorLogService.getAllErrorLogs());
    }

    // Get error by ID
    @GetMapping("/{id}")
    public ResponseEntity<ErrorLog> getErrorLogById(@PathVariable String id) {
        return ResponseEntity.ok(errorLogService.getErrorLogById(id));
    }

    // Get errors by severity
    @GetMapping("/severity/{severity}")
    public ResponseEntity<List<ErrorLog>> getErrorLogsBySeverity(
            @PathVariable ErrorSeverity severity) {
        return ResponseEntity.ok(errorLogService.getErrorLogsBySeverity(severity));
    }

    // Assign error to developer
    @PutMapping("/{id}/assign")
    public ResponseEntity<ErrorLog> assignErrorLog(
            @PathVariable String id,
            @RequestParam String assignedTo) {
        return ResponseEntity.ok(errorLogService.assignErrorLog(id, assignedTo));
    }

    // Mark error as resolved
    @PutMapping("/{id}/resolve")
    public ResponseEntity<ErrorLog> resolveErrorLog(@PathVariable String id) {
        return ResponseEntity.ok(errorLogService.resolveErrorLog(id));
    }
}
