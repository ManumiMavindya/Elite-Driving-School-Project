package lk.ijse.elitedrivingschoolproject.dao.custom.impl;

import lk.ijse.elitedrivingschoolproject.config.FactoryConfiguration;
import lk.ijse.elitedrivingschoolproject.dao.custom.InstructorDAO;
import lk.ijse.elitedrivingschoolproject.entity.Instructors;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.util.List;
import java.util.Optional;

public class InstructorDAOImpl implements InstructorDAO {

    private final FactoryConfiguration factoryConfiguration = FactoryConfiguration.getInstance();

    @Override
    public List<Instructors> getAll() throws Exception {

        Session session = factoryConfiguration.getSession();
        try {
            Query<Instructors> query = session.createQuery("from Instructors", Instructors.class);
            List<Instructors> instructors = query.list();
            return instructors;
        }finally {
            session.close();
        }
    }

    @Override
    public String getLastId() throws Exception {

        Session session = factoryConfiguration.getSession();
        try {
            Query<Instructors> query = session.createQuery("SELECT i.instructor_id FROM Instructors i ORDER BY i.instructor_id DESC", Instructors.class)
                    .setMaxResults(1);
            List<String> instructorList = query.list();
            if(instructorList.isEmpty()){
                return null;
            }
            return instructorList.getFirst();
        }finally {
            session.close();
        }
    }

    @Override
    public boolean save(Instructors instructor) throws Exception {

        Session session = factoryConfiguration.getSession();
        Transaction transaction = session.beginTransaction();
        try {
            session.persist(instructor);
            transaction.commit();
            return true;
        }catch (Exception e){
            transaction.rollback();
            return false;
        }finally {
            session.close();
        }
    }

    @Override
    public boolean update(Instructors instructor) throws Exception {

        Session session = factoryConfiguration.getSession();
        Transaction transaction = session.beginTransaction();
        try {
            session.merge(instructor);
            transaction.commit();
            return true;
        }catch (Exception e){
            transaction.rollback();
            return false;
        }finally {
            session.close();
        }
    }

    @Override
    public boolean delete(String id) throws Exception {

        Session session = factoryConfiguration.getSession();
        Transaction transaction = session.beginTransaction();
        try {
            Instructors instructor = (Instructors) session.get(Instructors.class, id);
            if(instructor != null){
                session.remove(instructor);
                transaction.commit();
                return true;
            }
            return false;
        }catch (Exception e){
            if(transaction != null){
                transaction.rollback();
            }
            e.printStackTrace();
            return false;
        }finally {
            session.close();
        }
    }

    @Override
    public List<String> getAllIds() throws Exception {

        Session session = factoryConfiguration.getSession();
        try {
            Query<String> query = session.createQuery("SELECT i.instructor_id FROM Instructors i", String.class);
            return query.list();
        }finally {
            session.close();
        }
    }

    @Override
    public Optional<Instructors> findById(String id) throws Exception {

        Session session = factoryConfiguration.getSession();
        try {
            Instructors instructor = session.get(Instructors.class, id);
            return Optional.ofNullable(instructor);
        }finally {
            session.close();
        }
    }

    @Override
    public String generateNewId() throws Exception {

        String lastId = null;
        try {
            lastId = getLastId();
        }catch (Exception e){
            throw new RuntimeException(e);
        }
        if (lastId == null) {
            return "I001";
        }else {
            int num = Integer.parseInt(lastId.substring(1));
            num++;
            return String.format("I%03d", num);
        }
    }

}
