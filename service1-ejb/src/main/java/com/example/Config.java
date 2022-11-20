package com.example;

import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.enterprise.context.Dependent;
import javax.enterprise.inject.Produces;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Configuration
@EnableJpaRepositories
@EnableTransactionManagement
public class Config {

    @Produces
    @Dependent
    @PersistenceContext(unitName = "soa")
    public EntityManager entityManager;
}
