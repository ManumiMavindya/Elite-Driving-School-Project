package lk.ijse.elitedrivingschoolproject.dao.custom;

import lk.ijse.elitedrivingschoolproject.dao.SuperDAO;

public interface QueryDAO extends SuperDAO {

    public int getStudentCountForLesson(String lessonId);
}
