package com.example.service1.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfig {

    @Bean
    public OpenAPI customOpenAPI() {
        String apiTitle = "SOA API service 1";
        return new OpenAPI().info(
                new Info().title(apiTitle).version("1.0.0").contact(
                        new Contact().name("Vladislav Kuznetsov and Dmitry Sviridov").email("kuznetsov.vladislav.vuz@gmail.com")
                )
        );
    }
}
