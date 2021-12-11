package com.movies.adapters.config;

import org.testcontainers.containers.PostgreSQLContainer;

public class PostgreSqlTestContainer extends PostgreSQLContainer<PostgreSqlTestContainer> {

    private static final String IMAGE_VERSION = "postgres:11";
    private static final String POSTGRES_USER = "test";
    private static final String POSTGRES_PASSWORD = "test";
    private static final String POSTGRES_DB_NAME = "test";
    private static final String POSTGRES_DB_SCHEMA_NAME = "movies";
    public static volatile PostgreSqlTestContainer container;

    private static final Object obj = new Object();

    private PostgreSqlTestContainer() {
        super(IMAGE_VERSION);
    }

    public static void startDbContainer() {
        if (container == null) {
            synchronized (obj) {
                if (container == null) {
                    container = new PostgreSqlTestContainer();
                    container.withInitScript("db/movies-schema.sql");
                    container.start();
                    initializeDbVariables();
                }
            }
        }
    }

    private static void initializeDbVariables() {
        System.setProperty("MOVIES_DB_HOST", container.getContainerIpAddress());
        System.setProperty("MOVIES_DB_PORT", String.valueOf(container.getFirstMappedPort()));
        System.setProperty("MOVIES_DB_NAME", POSTGRES_DB_NAME);
        System.setProperty("MOVIES_DB_SCHEMA", POSTGRES_DB_SCHEMA_NAME);
        System.setProperty("MOVIES_DB_USERNAME", POSTGRES_USER);
        System.setProperty("MOVIES_DB_PASSWORD", POSTGRES_PASSWORD);
    }
}
