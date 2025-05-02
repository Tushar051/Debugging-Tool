package com.dubbgingSystem.debugger.repository;

import com.dubbgingSystem.debugger.entity.ErrorLog;
import com.dubbgingSystem.debugger.enums.ErrorSeverity;
import com.dubbgingSystem.debugger.enums.ErrorStatus;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.time.LocalDateTime;
import java.util.List;


public interface ErrorLogRepository extends MongoRepository<ErrorLog, String> {

    List<ErrorLog> findByStatus(ErrorStatus status);
    List<ErrorLog> findBySeverity(ErrorSeverity status);
    List<ErrorLog> findByServiceName(String serviceName);

    List<ErrorLog> findByStatusAndSeverity(ErrorStatus status, ErrorSeverity severity);

    List<ErrorLog> findByTimeStampAfter(LocalDateTime date);
    List<ErrorLog> findByTimeStampBetween(LocalDateTime start, LocalDateTime end);

    List<ErrorLog> findByErrorMessageContainingIgnoreCase(String keyword);

    List<ErrorLog> findByAssignedTo(String email);
}
