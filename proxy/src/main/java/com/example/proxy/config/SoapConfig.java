package com.example.proxy.config;

import com.example.proxy.service.RouteService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.oxm.jaxb.Jaxb2Marshaller;

@Configuration
public class SoapConfig {
    @Bean
    public Jaxb2Marshaller marshaller() {
        Jaxb2Marshaller marshaller = new Jaxb2Marshaller();
        marshaller.setContextPath("com.example.proxy.model");
        return marshaller;
    }

    @Bean
    public RouteService routeService(Jaxb2Marshaller marshaller) {
        RouteService routeService = new RouteService();
        routeService.setDefaultUri("https://localhost:31001/ws");
        routeService.setMarshaller(marshaller);
        routeService.setUnmarshaller(marshaller);
        return routeService;
    }
}
