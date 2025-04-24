package com.hotelbooking;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import java.io.File;
import java.util.logging.Level;

public class HibernateSessionFactoryBuilder
{

    private final String debugDbPath = "src/main/resources/database/hotelbooking.db";
    private final String jarDbPath = "classes/database/hotelbooking.db";

    public SessionFactory createSessionFactory() {
        try {
            setLogLevels();
            String dbPath = resolveDbPath();

            Configuration configuration = new Configuration();
            configuration.configure("hibernate.cfg.xml");
            configuration.setProperty("hibernate.connection.url", "jdbc:sqlite:" + dbPath);
            return configuration.buildSessionFactory();
        } catch (Throwable ex) {
            System.err.println("Initial SessionFactory creation failed: " + ex);
            throw new ExceptionInInitializerError(ex);
        }
    }

    private String resolveDbPath() {
        File jarFile = new File(HibernateSessionFactoryBuilder.class
                .getProtectionDomain()
                .getCodeSource()
                .getLocation()
                .getPath());
        File jarDir = jarFile.getParentFile();
        String jarDatabasePath = new File(jarDir, jarDbPath).getAbsolutePath();

        return new File(debugDbPath).exists() ? debugDbPath : jarDatabasePath;
    }

    private void setLogLevels() {
        java.util.logging.Logger.getLogger("org.hibernate").setLevel(Level.SEVERE);
    }
}
