package com.example.config;

import com.example.service.RouteService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import java.util.Properties;

@Configuration
public class EjbConnectivity {

    @Bean
    public Context context() throws NamingException {
        Properties jndiProps = new Properties();
        jndiProps.put("java.naming.factory.initial", "org.jboss.naming.remote.client.InitialContextFactory");
        jndiProps.put("jboss.naming.client.ejb.context", true);
        jndiProps.put("java.naming.provider.url", "http-remoting://localhost:36500");
        return new InitialContext(jndiProps);
    }

    @Bean
    public RouteService routeService(Context context) throws NamingException {
        return (RouteService) context.lookup(this.getFullName(RouteService.class));
    }

    private String getFullName(Class classType) {
        String moduleName = "service2-ejb";
        String beanName = classType.getSimpleName();
        String viewClassName = classType.getName();
        return moduleName + "/" + beanName + "!" + viewClassName;
    }
}
