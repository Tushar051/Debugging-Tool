package com.dubbgingSystem.debugger.service;

import com.dubbgingSystem.debugger.entity.ErrorLog;
import com.dubbgingSystem.debugger.enums.ErrorSeverity;
import com.dubbgingSystem.debugger.enums.ErrorStatus;
import com.dubbgingSystem.debugger.repository.ErrorLogRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class ErrorLogService {

    @Autowired
    private ErrorLogRepository errorLogRepository;

    // Log new Error
    public ErrorLog logError(ErrorLog errorLog){
    errorLog.setTimeStamp(LocalDateTime.now());
    errorLog.setStatus(ErrorStatus.OPEN);
    return errorLogRepository.save(errorLog);
    }

    //Assign error to a developer
    public ErrorLog assignError(String errorId, String assignedTo){
        ErrorLog error = errorLogRepository.findById(errorId)
                .orElseThrow(() -> new RuntimeException(errorId));

        error.setAssignedTo(assignedTo);
        error.setStatus(ErrorStatus.IN_PROGRESS);
        error.setLastUpdate(LocalDateTime.now());

        return errorLogRepository.save(error);
    }

    //Mark error as resolved
    public ErrorLog resolveError(String errorId){
        ErrorLog error = errorLogRepository.findById(errorId)
                .orElseThrow(() -> new RuntimeException(errorId));
        error.setStatus(ErrorStatus.RESOLVED);
        error.setResolvedAt(LocalDateTime.now());

        return errorLogRepository.save(error);
    }

    // Fetch error by filter
     public List<ErrorLog> getErrorBySeverity(ErrorSeverity severity){
        return errorLogRepository.findBySeverity(severity);
     }

     public List<ErrorLog> getErrorByStatus(ErrorStatus status){
        return errorLogRepository.findByStatus(status);
     }

     public List<ErrorLog> getAllErrors(){
        return errorLogRepository.findAll();
     }

    public ErrorLog getErrorById(String id) {
        return errorLogRepository.findById(id)
                .orElseThrow(RuntimeException::new);
    }
}
