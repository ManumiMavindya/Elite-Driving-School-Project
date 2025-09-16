package lk.ijse.elitedrivingschoolproject.config;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class FactoryConfiguration {

    private static FactoryConfiguration factoryConfiguration;
    private SessionFactory sessionFactory;

    private FactoryConfiguration() {

        Configuration configuration = new Configuration();
        configuration.configure("hibernate.cfg.xml"); //1. load configuration (xml)

        //2. load entity classes





        //3. create session factory object
        sessionFactory = configuration.buildSessionFactory();

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
