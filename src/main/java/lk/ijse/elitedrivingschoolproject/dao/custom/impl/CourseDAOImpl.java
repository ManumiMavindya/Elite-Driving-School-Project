package lk.ijse.elitedrivingschoolproject.dao.custom.impl;

import lk.ijse.elitedrivingschoolproject.config.FactoryConfiguration;
import lk.ijse.elitedrivingschoolproject.dao.custom.CourseDAO;
import lk.ijse.elitedrivingschoolproject.entity.Course;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.util.List;
import java.util.Optional;

public class CourseDAOImpl implements CourseDAO {

    private final FactoryConfiguration factoryConfiguration = FactoryConfiguration.getInstance();

    @Override
    public List<Course> getAll() throws Exception {

        Session session = factoryConfiguration.getSession();
        try {
            Query<Course> query = session.createQuery("from Course", Course.class);
            List<Course> courses = query.list();
            return courses;
        }finally {
            session.close();
        }
    }

    @Override
    public String getLastId() throws Exception {

        Session session = factoryConfiguration.getSession();
        try{
            Query<String> query = session.createQuery("SELECT c.course_id FROM Course c ORDER BY c.course_id DESC", String.class);
                     .setMaxResults(1);
            List<String> courseIds = query.list();
            if(courseIds.isEmpty()){
                return null;
            }
            return courseIds.getFirst();
        }finally {
            session.close();
        }
    }

    @Override
    public boolean save(Course course) throws Exception {

        Session session = factoryConfiguration.getSession();
        Transaction  transaction = session.beginTransaction();
        try {
            session.persist(course);
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
    public boolean update(Course course) throws Exception {

        Session session = factoryConfiguration.getSession();
        Transaction  transaction = session.beginTransaction();
        try {
            session.merge(course);
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
        Transaction  transaction = session.beginTransaction();
        try {
            Course course = (Course) session.get(Course.class, id);
            if(course != null){
                session.remove(course);
                transaction.commit();
                return true;
            }
            return false;
        }catch (Exception e){
            if (transaction != null) {
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
            Query<String> query = session.createQuery("SELECT c.course_id FROM Course c", String.class);
            return query.list();
        }finally {
            session.close();
        }
    }

    @Override
    public Optional<Course> findById(String id) throws Exception {

        Session session = factoryConfiguration.getSession();
        try {
            Course course = session.get(Course.class, id);
            return Optional.ofNullable(course);
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
            return "C001";
        }else {
            int num = Integer.parseInt(lastId.substring(1));
            num++;
            return String.format("C%03d", num);
        }
    }
}
