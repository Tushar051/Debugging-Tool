package com.dubbgingSystem.debugger.controller;

import com.dubbgingSystem.debugger.entity.ErrorLog;
import com.dubbgingSystem.debugger.enums.ErrorSeverity;
import com.dubbgingSystem.debugger.service.ErrorLogService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/api/errors")
public class ErrorLogController {

    @Autowired
    private ErrorLogService errorLogService;

    @PostMapping
    public ResponseEntity<ErrorLog> logError(@RequestBody ErrorLog errorLog){
        ErrorLog savedError = errorLogService.logError(errorLog);
        return new ResponseEntity<>
                (savedError, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<ErrorLog>> getAllErrors(){
        List<ErrorLog> allErrors = errorLogService.getAllErrors();
        return new ResponseEntity<>(allErrors, HttpStatus.OK);
    }

    @PutMapping("/{id}/assign")
    public ResponseEntity<ErrorLog> assignError(
            @PathVariable String id,
            @RequestParam String assignedTo){

        try{
            ErrorLog assignedError = errorLogService.assignError(id, assignedTo);
            return new ResponseEntity<>(assignedError, HttpStatus.OK);
        }catch (Exception e){
            System.out.println(e.getMessage());
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }


    @PutMapping("/{id}/resolve")
    public ResponseEntity<ErrorLog> resolveError(@PathVariable String id){
        try{
            ErrorLog errorLog = errorLogService.resolveError(id);
            return new ResponseEntity<>(errorLog, HttpStatus.OK);
        }catch (Exception e){
            System.out.println(e.getMessage());
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }


    @GetMapping("/{id}")
    public ResponseEntity<ErrorLog> getErrorById(@PathVariable String id){
        ErrorLog errorById = errorLogService.getErrorById(id);
        return new ResponseEntity<>(errorById, HttpStatus.NOT_FOUND);
    }

    @GetMapping("/severity/{severity}")
    public ResponseEntity<List<ErrorLog>> getErrorBySeverity
            (@PathVariable ErrorSeverity severity){
        List<ErrorLog> errors = errorLogService.getErrorBySeverity(severity);
        return new ResponseEntity<>(errors, HttpStatus.OK);
    }
}
