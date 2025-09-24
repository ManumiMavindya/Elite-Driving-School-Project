package lk.ijse.elitedrivingschoolproject.dao.custom.impl;

import org.hibernate.query.Query;
import lk.ijse.elitedrivingschoolproject.config.FactoryConfiguration;
import lk.ijse.elitedrivingschoolproject.dao.custom.QueryDAO;
import org.hibernate.Session;

public class QueryDAOImpl implements QueryDAO {

    private final FactoryConfiguration factoryConfiguration = FactoryConfiguration.getInstance();

    @Override
    public int getStudentCountForLesson(String lessonId) {

        Session session = factoryConfiguration.getSession();
        try {
            String hql = "SELECT COUNT(s.student_id) FROM Students s JOIN s.lessons l WHERE l.lesson_id";
            Query<Long> query = session.createQuery(hql, Long.class);
            query.setParameter("lessonId", lessonId);
            Long count = query.uniqueResult();
            return count != null ? count.intValue() : 0;
        }finally {
            session.close();
        }
    }

}
