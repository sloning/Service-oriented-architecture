package com.example.soa.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfig {

    @Bean
    public OpenAPI customOpenAPI() {
        String apiTitle = "SOA API";
        return new OpenAPI().info(
                new Info().title(apiTitle).version("1.0.0")
        );
    }
}
