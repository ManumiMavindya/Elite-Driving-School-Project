package lk.ijse.elitedrivingschoolproject.config;

import lk.ijse.elitedrivingschoolproject.entity.*;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class FactoryConfiguration {

    private static FactoryConfiguration factoryConfiguration;
    private SessionFactory sessionFactory;

    private FactoryConfiguration() {

        Properties properties = new Properties();

        try {
            properties.load(FactoryConfiguration.class.getClassLoader().getResourceAsStream("hibernate.properties"));

            Configuration config = new Configuration();
            config.addProperties(properties);

            config.addAnnotatedClass(Students.class);
            config.addAnnotatedClass(StudentCourseDetails.class);
            config.addAnnotatedClass(Course.class);
            config.addAnnotatedClass(Instructors.class);
            config.addAnnotatedClass(Lessons.class);
            config.addAnnotatedClass(Payments.class);
            config.addAnnotatedClass(User.class);

            sessionFactory = config.buildSessionFactory();

        }catch (Exception e){
            e.printStackTrace();
            throw new RuntimeException("error in hibernate properties" ,e);
        }

    }

    public static FactoryConfiguration getInstance() {

        return factoryConfiguration == null ?
                factoryConfiguration = new FactoryConfiguration() :
                factoryConfiguration;
    }

    public Session getSession() {
        Session session = sessionFactory.getCurrentSession();
        return session;
    }

    public Session getCurrentSession() {
        return sessionFactory.getCurrentSession();
    }


}
