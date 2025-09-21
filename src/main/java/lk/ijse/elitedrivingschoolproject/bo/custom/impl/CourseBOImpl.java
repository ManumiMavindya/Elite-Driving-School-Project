package lk.ijse.elitedrivingschoolproject.bo.custom.impl;

import lk.ijse.elitedrivingschoolproject.bo.custom.CourseBO;
import lk.ijse.elitedrivingschoolproject.bo.util.EntityDTOConverter;
import lk.ijse.elitedrivingschoolproject.dao.DAOFactory;
import lk.ijse.elitedrivingschoolproject.dao.DAOTypes;
import lk.ijse.elitedrivingschoolproject.dao.custom.CourseDAO;
import lk.ijse.elitedrivingschoolproject.dto.CourseDTO;
import lk.ijse.elitedrivingschoolproject.entity.Course;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class CourseBOImpl implements CourseBO {

    private final CourseDAO courseDAO = (CourseDAO) DAOFactory.getInstance().getDAO(DAOTypes.COURSE);
    private final EntityDTOConverter entityDTOConverter = new EntityDTOConverter();


    @Override
    public List<CourseDTO> getAllCourses() throws Exception {

        List<Course> courses = courseDAO.getAll();
        List<CourseDTO> courseDTOs = new ArrayList<>();

        for (Course course : courses) {
            courseDTOs.add(entityDTOConverter.getCourseDTO(course));
        }
        return courseDTOs;

    }

    @Override
    public boolean saveCourse(CourseDTO courseDTO) throws Exception {

        Optional<Course> course = courseDAO.findById(courseDTO.getCourseId());
        if (course.isPresent()) {
            throw new Exception("Course already exists");
        }
        return courseDAO.save(entityDTOConverter.getCourseEntity(courseDTO));
    }

    @Override
    public boolean updateCourse(CourseDTO courseDTO) throws Exception {

        Optional<Course> course = courseDAO.findById(courseDTO.getCourseId());
        if (course.isEmpty()) {
            throw new Exception("Course not found");
        }
        return courseDAO.update(entityDTOConverter.getCourseEntity(courseDTO));
    }

    @Override
    public boolean deleteCourse(String id) throws Exception {

        Optional<Course> course = courseDAO.findById(id);
        if (course.isEmpty()) {
            throw new Exception("Course not found");
        }
        return courseDAO.delete(id);
    }

    @Override
    public List<String> getAllCoursesIds() throws Exception {

        return courseDAO.getAllIds();
    }

    @Override
    public Optional<CourseDTO> findCourseById(String id) throws Exception {

        Optional<Course> course = courseDAO.findById(id);
        if (course.isPresent()) {
            return Optional.of(entityDTOConverter.getCourseDTO(course.get()));
        }
        return Optional.empty();
    }
}
