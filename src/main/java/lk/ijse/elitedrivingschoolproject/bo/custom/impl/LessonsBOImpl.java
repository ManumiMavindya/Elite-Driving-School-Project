package lk.ijse.elitedrivingschoolproject.bo.custom.impl;

import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import lk.ijse.elitedrivingschoolproject.bo.custom.LessonsBO;
import lk.ijse.elitedrivingschoolproject.bo.exception.DuplicateException;
import lk.ijse.elitedrivingschoolproject.bo.exception.NotFoundException;
import lk.ijse.elitedrivingschoolproject.bo.util.EntityDTOConverter;
import lk.ijse.elitedrivingschoolproject.dao.DAOFactory;
import lk.ijse.elitedrivingschoolproject.dao.DAOTypes;
import lk.ijse.elitedrivingschoolproject.dao.custom.CourseDAO;
import lk.ijse.elitedrivingschoolproject.dao.custom.InstructorDAO;
import lk.ijse.elitedrivingschoolproject.dao.custom.LessonsDAO;
import lk.ijse.elitedrivingschoolproject.dao.custom.QueryDAO;
import lk.ijse.elitedrivingschoolproject.dto.LessonsDTO;
import lk.ijse.elitedrivingschoolproject.entity.Lessons;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class LessonsBOImpl implements LessonsBO {

    private final LessonsDAO lessonsDAO = (LessonsDAO) DAOFactory.getInstance().getDAO(DAOTypes.LESSONS);
    private final CourseDAO courseDAO = (CourseDAO) DAOFactory.getInstance().getDAO(DAOTypes.COURSE);
    private final InstructorDAO instructorDAO = (InstructorDAO) DAOFactory.getInstance().getDAO(DAOTypes.INSTRUCTORS);
    private final QueryDAO queryDAO = (QueryDAO) DAOFactory.getInstance().getDAO(DAOTypes.QUERY);
    private final EntityDTOConverter entityDTOConverter = new EntityDTOConverter();

    @Override
    public List<LessonsDTO> getAllLessons() throws Exception {

        List<Lessons> lessons = lessonsDAO.getAll();
        List<LessonsDTO> lessonsDTOs = new ArrayList<>();
        for (Lessons lesson : lessons) {
            lessonsDTOs.add(entityDTOConverter.getLessonsDTO(lesson));
        }
        return lessonsDTOs;
    }

    @Override
    public String getLastLessonId() throws Exception {

        return lessonsDAO.getLastId();
    }

    @Override
    public boolean saveLesson(LessonsDTO lesson) throws Exception {

        boolean courseExist = courseDAO.findById(lesson.getCourseId()).isPresent();

        boolean instructorExist = instructorDAO.findById(lesson.getCourseId()).isPresent();

        if (courseExist && instructorExist) {
            return lessonsDAO.save(entityDTOConverter.getLessonsEntity(lesson));
        }
        throw new Exception("Course or Instructor not found");
    }

    @Override
    public boolean updateLesson(LessonsDTO lesson) throws Exception {

        Optional<Lessons> lessons = lessonsDAO.findById(lesson.getLessonId());
        if (lessons.isEmpty()) {
            throw new DuplicateException("Lesson not found");
        }
        return lessonsDAO.update(entityDTOConverter.getLessonsEntity(lesson));
    }

    @Override
    public boolean deleteLesson(String id) throws Exception {

        Optional<Lessons> lessons = lessonsDAO.findById(id);
        if (lessons.isEmpty()) {
            throw new NotFoundException("Lesson not found");
        }

        int studentEnrolled = queryDAO.getStudentCountForLesson(id);
        if (studentEnrolled > 0) {
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION,
            "Are you sure you want to delete?",
                    ButtonType.YES, ButtonType.NO);

            ButtonType result = alert.showAndWait().orElse(ButtonType.NO);
            if (result != ButtonType.YES) {
                return false;
            }
        }
        return lessonsDAO.delete(id);
    }

    @Override
    public List<String> getAllLessonIds() throws Exception {

        return lessonsDAO.getAllIds();

    }

    @Override
    public Optional<LessonsDTO> findByLessonId(String id) throws Exception {

        Optional<Lessons> lessons = lessonsDAO.findById(id);
        if (lessons.isPresent()) {
            return Optional.of(entityDTOConverter.getLessonsDTO(lessons.get()));
        }
        return Optional.empty();
    }
}
