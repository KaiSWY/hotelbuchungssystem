package com.hotelbooking;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import java.io.File;
import java.util.logging.Level;

public enum HibernateUtil
{
    INSTANCE;
    private final String debugDbPath = "src/main/resources/database/hotelbooking.db";
    private final String jarDbPath = "classes/database/hotelbooking.db";
    private final SessionFactory sessionFactory = buildSessionFactory();

    private SessionFactory buildSessionFactory()
    {
        try
        {
            String dbPath = getDbPath();
            setLogLevels();

            Configuration configuration = new Configuration();
            configuration.configure("hibernate.cfg.xml");
            configuration.setProperty("hibernate.connection.url", "jdbc:sqlite:" + dbPath);
            return configuration.buildSessionFactory();
        } catch (Throwable ex)
        {
            System.err.println("Initial SessionFactory creation failed: " + ex);
            throw new ExceptionInInitializerError(ex);
        }
    }

    private String getDbPath()
    {
        File jarFile = new File(HibernateUtil.class.getProtectionDomain().getCodeSource().getLocation().getPath());
        File jarDir = jarFile.getParentFile();
        String jarDatabasePath = new File(jarDir, jarDbPath).getAbsolutePath();

        return new File(debugDbPath).exists() ? debugDbPath : jarDatabasePath;
    }

    private void setLogLevels(){
        java.util.logging.Logger.getLogger("org.hibernate").setLevel(Level.SEVERE);
    }

    public SessionFactory getSessionFactory()
    {
        return sessionFactory;
    }

    public void shutdown()
    {
        getSessionFactory().close();
    }
}
