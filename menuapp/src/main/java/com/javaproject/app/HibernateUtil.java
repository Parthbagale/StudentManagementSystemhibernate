package com.javaproject.app;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class HibernateUtil {

    // Singleton SessionFactory instance
    private static final SessionFactory sessionFactory;

    static {
        try {
            // Load settings from hibernate.cfg.xml
            sessionFactory = new Configuration()
                    .configure("hibernate.cfg.xml") // must be in src/main/resources
                    .buildSessionFactory();
        } catch (Throwable ex) {
            System.err.println("❌ Initial SessionFactory creation failed." + ex);
            throw new ExceptionInInitializerError(ex);
        }
    }

    // Public method to return SessionFactory
    public static SessionFactory getSessionFactory() {
        return sessionFactory;
    }    
        public static void shutdown() {
            getSessionFactory().close();
        

    }
}
