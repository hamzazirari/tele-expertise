package com.telexpertise.util;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

public class JPAUtil {

    // Une seule fabrique pour toute l'application
    private static final EntityManagerFactory emf =
            Persistence.createEntityManagerFactory("teleExpertisePU");

    // Donne un nouvel EntityManager pour parler à la base
    public static EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    // À appeler quand l'application s'arrête
    public static void close() {
        if (emf != null && emf.isOpen()) {
            emf.close();
        }
    }
}