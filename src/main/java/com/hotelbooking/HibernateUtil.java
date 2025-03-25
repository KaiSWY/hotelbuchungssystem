package com.hotelbooking;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import java.io.File;
import java.util.logging.Level;
import java.util.logging.Logger;

public class HibernateUtil
{
    private static final String debugDbPath = "src/main/resources/database/hotelbooking.db";
    private static final String jarDbPath = "classes/database/hotelbooking.db";
    private static final SessionFactory sessionFactory = buildSessionFactory();

    private static SessionFactory buildSessionFactory()
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

    private static String getDbPath()
    {
        File jarFile = new File(HibernateUtil.class.getProtectionDomain().getCodeSource().getLocation().getPath());
        File jarDir = jarFile.getParentFile();
        String jarDatabasePath = new File(jarDir, jarDbPath).getAbsolutePath();

        return new File(debugDbPath).exists() ? debugDbPath : jarDatabasePath;
    }

    private static void setLogLevels(){
        java.util.logging.Logger.getLogger("org.hibernate").setLevel(Level.SEVERE);
    }

    public static SessionFactory getSessionFactory()
    {
        return sessionFactory;
    }

    public static void shutdown()
    {
        getSessionFactory().close();
    }
}
