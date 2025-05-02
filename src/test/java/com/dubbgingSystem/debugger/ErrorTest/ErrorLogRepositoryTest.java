package com.dubbgingSystem.debugger.ErrorTest;

import com.dubbgingSystem.debugger.entity.ErrorLog;
import com.dubbgingSystem.debugger.enums.ErrorSeverity;
import com.dubbgingSystem.debugger.enums.ErrorStatus;
import com.dubbgingSystem.debugger.repository.ErrorLogRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class ErrorLogRepositoryTest {

    @Autowired
    private ErrorLogRepository errorLogRepository;

    @Test
    void testSaveError(){
        ErrorLog error = new ErrorLog();
        error.setErrorMessage("NullPointer in UserController");
        error.setSeverity(ErrorSeverity.CRITICAL);
        error.setStatus(ErrorStatus.OPEN);
        error.setEndpoint("/api/users");
        errorLogRepository.save(error);
    }

}
