package com.dubbgingSystem.debugger;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfig {

    public OpenAPI debuggingToolAPI(){
        return new OpenAPI()
                .info(new Info()
                        .title("Debugging Tool API")
                        .description("API for tracking and managing backend errors")
                        );
    }
}
