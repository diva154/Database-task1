package jm.task.core.jdbc.util;

import jm.task.core.jdbc.model.User;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.cfg.Environment;

import java.util.Properties;


public class Util {
    private static SessionFactory sessionFactory;


    public static SessionFactory getSessionFactory() {
        if (sessionFactory == null) {
            try {
                Configuration configuration = new Configuration();

                //database connection settings
                Properties settings = new Properties();
                settings.put(Environment.DRIVER, "com.mysql.cj.jdbc.Driver");
                settings.put(Environment.URL, "jdbc:mysql://localhost:3306/HibernateTask_db?useSSL=false&serverTimezone=UTC");
                settings.put(Environment.USER, "root");
                settings.put(Environment.PASS, "Travelikediva@154");
                settings.put(Environment.DIALECT, "org.hibernate.dialect.MySQL8Dialect");

                //echo all executed SQL to console
                settings.put(Environment.SHOW_SQL, "true");

                configuration.setProperties(settings);

                //Register annotated entity classes
                configuration.addAnnotatedClass(User.class);

                //Call buildSessionFactory
                sessionFactory = configuration.buildSessionFactory();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return sessionFactory;
    }
}


