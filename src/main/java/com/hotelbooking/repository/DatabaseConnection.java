package com.hotelbooking.repository;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class DatabaseConnection {
    private static SessionFactory sessionFactory;

    private DatabaseConnection() {
        // private constructor to avoid multiple instances
    }

    public static SessionFactory getSessionFactory() {
        if (sessionFactory == null) {
            try {
                sessionFactory = new Configuration()
                        .configure("hibernate.cfg.xml")  // Lädt die Konfigurationsdatei
                        .buildSessionFactory();
            } catch (Exception ex) {
                ex.printStackTrace();
                throw new RuntimeException("Error when setting up the SessionFactory: " + ex.getMessage());
            }
        }
        return sessionFactory;
    }

    public static void shutdown() {
        if (sessionFactory != null) {
            sessionFactory.close();
        }
    }
}
