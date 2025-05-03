package com.dubbgingSystem.debugger.entity;


import com.dubbgingSystem.debugger.enums.ErrorSeverity;
import com.dubbgingSystem.debugger.enums.ErrorStatus;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@Document(collection = "error_log")
@RequiredArgsConstructor
public class ErrorLog {


    @Id
    private String Id;


    @NotBlank(message = "errorMessage is mandatory")
    private String errorMessage;


    @NotBlank(message = "exceptionType is mandatory")
    private String exceptionType;


    @NotBlank(message = "stackTrace is mandatory")
    private String stackTrace;


    @NotBlank(message = "httpMethod is mandatory")
    private String httpMethod;


    @NotBlank(message = "endPoint is mandatory")
    private String endPoint;

    @NotNull(message = "severity is mandatory")
    private ErrorSeverity severity = ErrorSeverity.MEDIUM; // Default value

    @NotNull(message = "status is mandatory")
    private ErrorStatus status = ErrorStatus.OPEN; // Default value

    private String assignedTo;

    private String environment = "DEV"; // Default value

    private String serviceName;

    private LocalDateTime timeStamp = LocalDateTime.now(); // Auto-set

    private LocalDateTime resolvedAt;

    private LocalDateTime lastUpdate;



    private String requestBody;
    private String requestParam;

    private String userId;

    private String clientIp;

    private String correlationId;


    public String getId() {
        return Id;
    }

    public void setId(String id) {
        Id = id;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }

    public String getExceptionType() {
        return exceptionType;
    }

    public void setExceptionType(String exceptionType) {
        this.exceptionType = exceptionType;
    }

    public String getStackTrace() {
        return stackTrace;
    }

    public void setStackTrace(String stackTrace) {
        this.stackTrace = stackTrace;
    }

    public String getHttpMethod() {
        return httpMethod;
    }

    public void setHttpMethod(String httpMethod) {
        this.httpMethod = httpMethod;
    }

    public String getEndPoint() {
        return endPoint;
    }

    public void setEndPoint(String endPoint) {
        this.endPoint = endPoint;
    }

    public ErrorSeverity getSeverity() {
        return severity;
    }

    public void setSeverity(ErrorSeverity severity) {
        this.severity = severity;
    }

    public ErrorStatus getStatus() {
        return status;
    }

    public void setStatus(ErrorStatus status) {
        this.status = status;
    }

    public String getAssignedTo() {
        return assignedTo;
    }

    public void setAssignedTo(String assignedTo) {
        this.assignedTo = assignedTo;
    }

    public String getEnvironment() {
        return environment;
    }

    public void setEnvironment(String environment) {
        this.environment = environment;
    }

    public String getServiceName() {
        return serviceName;
    }

    public void setServiceName(String serviceName) {
        this.serviceName = serviceName;
    }

    public LocalDateTime getTimeStamp() {
        return timeStamp;
    }

    public void setTimeStamp(LocalDateTime timeStamp) {
        this.timeStamp = timeStamp;
    }

    public LocalDateTime getResolvedAt() {
        return resolvedAt;
    }

    public void setResolvedAt(LocalDateTime resolvedAt) {
        this.resolvedAt = resolvedAt;
    }

    public LocalDateTime getLastUpdate() {
        return lastUpdate;
    }

    public void setLastUpdate(LocalDateTime lastUpdate) {
        this.lastUpdate = lastUpdate;
    }

    public String getRequestBody() {
        return requestBody;
    }

    public void setRequestBody(String requestBody) {
        this.requestBody = requestBody;
    }

    public String getRequestParam() {
        return requestParam;
    }

    public void setRequestParam(String requestParam) {
        this.requestParam = requestParam;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getClientIp() {
        return clientIp;
    }

    public void setClientIp(String clientIp) {
        this.clientIp = clientIp;
    }

    public String getCorrelationId() {
        return correlationId;
    }

    public void setCorrelationId(String correlationId) {
        this.correlationId = correlationId;
    }

    public void setLastUpdated(LocalDateTime now) {

    }
}
