package lk.ijse.elitedrivingschoolproject.bo.custom;

import lk.ijse.elitedrivingschoolproject.dto.LessonsDTO;

import java.util.List;
import java.util.Optional;

public interface LessonsBO {

    public List<LessonsDTO> getAllLessons() throws Exception;

    public String getLastLessonId() throws Exception;

    public boolean saveLesson(LessonsDTO lesson) throws Exception;

    public boolean updateLesson(LessonsDTO lesson) throws Exception;

    public boolean deleteLesson(String id) throws Exception;

    public List<String> getAllLessonIds() throws Exception;

    public Optional<LessonsDTO> findByLessonId(String id) throws Exception;

    String generateNewLessonId() throws Exception;
}
