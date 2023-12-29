package com.fabio.neostoreapi.utils;


import java.util.HashMap;
import java.util.Map;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.enterprise.inject.Produces;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

@ApplicationScoped
public class EntityManagerProducer {

    public static final String PERSISTENCE_UNIT_NAME = "neostore-api";

    private EntityManagerFactory entityManagerFactory;

    @Produces
    @ApplicationScoped
    public EntityManager createEntityManager() {
    	if (entityManagerFactory == null) {
    		Map<String, String> properties = new HashMap<>();
            properties.put("jakarta.persistence.jdbc.url", System.getenv("DB_URL"));
            properties.put("jakarta.persistence.jdbc.user", System.getenv("DB_USER"));
            properties.put("jakarta.persistence.jdbc.password", System.getenv("DB_PASSWORD"));
            entityManagerFactory = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME, properties);
        }
    	return entityManagerFactory.createEntityManager();
    }

   
}
