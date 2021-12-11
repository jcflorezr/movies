package com.movies.adapters.config;

import org.springframework.context.ApplicationContextInitializer;
import org.springframework.context.ConfigurableApplicationContext;

public class PostgreSqlInitializer implements ApplicationContextInitializer<ConfigurableApplicationContext> {

    @Override
    public void initialize(ConfigurableApplicationContext applicationContext) {
        if (PostgreSqlTestContainer.container == null) {
            PostgreSqlTestContainer.startDbContainer();
        }
    }
}
