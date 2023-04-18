package org.example.cfg;

import org.example.utility.Customer;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class ConfigurationClass {

    private static SessionFactory sessionFactory = null;

    public static SessionFactory getSessionFactory() {
        // Create configuration
        if (sessionFactory == null){
            Configuration configuration = new Configuration();
            configuration.configure("hibernate.cfg.xml");
            configuration.addAnnotatedClass(Customer.class);

            // Create session factory
            sessionFactory = configuration.buildSessionFactory();

        }
        return sessionFactory;
    }
}
