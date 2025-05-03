package com.dubbgingSystem.debugger.service;

import com.dubbgingSystem.debugger.entity.ErrorLog;
import com.dubbgingSystem.debugger.enums.ErrorSeverity;
import com.dubbgingSystem.debugger.enums.ErrorStatus;
import com.dubbgingSystem.debugger.exception.ErrorLogNotFoundException;
import com.dubbgingSystem.debugger.repository.ErrorLogRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class ErrorLogService {

    @Autowired
    private ErrorLogRepository errorLogRepository;

    public ErrorLog createErrorLog(ErrorLog errorLog) {
        if (errorLog.getSeverity() == null) {
            errorLog.setSeverity(ErrorSeverity.MEDIUM);
        }
        if (errorLog.getStatus() == null) {
            errorLog.setStatus(ErrorStatus.OPEN);
        }
        errorLog.setTimeStamp(LocalDateTime.now());
        return errorLogRepository.save(errorLog);
    }


    public List<ErrorLog> getAllErrorLogs() {
        return errorLogRepository.findAll();
    }

    public ErrorLog getErrorLogById(String id) {
        return errorLogRepository.findById(id)
                .orElseThrow(() -> new ErrorLogNotFoundException(id));
    }

    public List<ErrorLog> getErrorLogsBySeverity(ErrorSeverity severity) {
        return errorLogRepository.findBySeverity(severity);
    }

    public ErrorLog assignErrorLog(String id, String assignedTo) {
        ErrorLog errorLog = getErrorLogById(id);
        errorLog.setAssignedTo(assignedTo);
        errorLog.setStatus(ErrorStatus.IN_PROGRESS);
        errorLog.setLastUpdated(LocalDateTime.now());
        return errorLogRepository.save(errorLog);
    }

    public ErrorLog resolveErrorLog(String id) {
        ErrorLog errorLog = getErrorLogById(id);
        errorLog.setStatus(ErrorStatus.RESOLVED);
        errorLog.setResolvedAt(LocalDateTime.now());
        return errorLogRepository.save(errorLog);
    }
}
