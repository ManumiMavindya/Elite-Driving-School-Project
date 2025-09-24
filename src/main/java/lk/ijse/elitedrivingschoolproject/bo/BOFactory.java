package lk.ijse.elitedrivingschoolproject.bo;

import lk.ijse.elitedrivingschoolproject.bo.custom.impl.*;

public class BOFactory {

    private static BOFactory boFactory;

    private BOFactory() {}

    public static BOFactory getInstance() {

        return boFactory == null ? (boFactory = new BOFactory()) : boFactory;
    }

    public <Hello extends SuperBO> Hello getBO(BOTypes type) {
        return switch (type){
            case COURSE -> (Hello) new CourseBOImpl();
            case INSTRUCTOR -> (Hello) new InstructorBOImpl();
            case LESSONS -> (Hello) new LessonsBOImpl();
            case PAYMENTS -> (Hello) new PaymentBOImpl();
            case QUERY -> (Hello) new QueryBOImpl();
            case STUDENT_COURSE_DETAILS -> (Hello) new StudentCourseDetailBOImpl();
            case STUDENT -> (Hello) new StudentBOImpl();
            case USER -> (Hello) new UserBOImpl();
        };
    }

}
