package lk.ijse.elitedrivingschoolproject.dao;

import lk.ijse.elitedrivingschoolproject.dao.custom.impl.*;

public class DAOFactory {
    private static DAOFactory daoFactory;
    private DAOFactory() {}

    public static DAOFactory getInstance() {
        return daoFactory == null ?
                (daoFactory = new DAOFactory()) : daoFactory;
    }

    public <T extends SuperDAO> T getDAO(DAOTypes daoTypes) {
        return switch (daoTypes){

            case COURSE -> (T) new CourseDAOImpl();

            case INSTRUCTORS ->  (T) new InstructorDAOImpl();

            case LESSONS -> (T) new LessonsDAOImpl();

            case PAYMENTS -> (T) new PaymentDAOImpl();

            case STUDENT_COURSE_DETAILS -> (T) new StudentCourseDetailsDAOImpl();

            case STUDENTS -> (T) new StudentsDAOImpl();

            case USER -> (T) new UserDAOImpl();

            case QUERY -> (T) new QueryDAOImpl();
        };
    }
}
