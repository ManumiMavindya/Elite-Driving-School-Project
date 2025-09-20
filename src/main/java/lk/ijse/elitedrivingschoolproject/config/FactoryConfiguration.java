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

        try{
            properties.load(new FileInputStream("hibernate.xnl"));
        }catch(IOException e){
            e.printStackTrace();
        }
        Configuration cfg = new Configuration()
                .addProperties(properties)
                .addAnnotatedClass(Course.class)
                .addAnnotatedClass(Instructors.class)
                .addAnnotatedClass(Lessons.class)
                .addAnnotatedClass(Students.class)
                .addAnnotatedClass(Payments.class)
                .addAnnotatedClass(StudentCourseDetails.class)
                .addAnnotatedClass(User.class);

        sessionFactory = cfg.buildSessionFactory();

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


}
