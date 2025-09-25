package lk.ijse.elitedrivingschoolproject.bo.custom;

import lk.ijse.elitedrivingschoolproject.bo.SuperBO;
import lk.ijse.elitedrivingschoolproject.dto.CourseDTO;

import java.util.List;
import java.util.Optional;

public interface CourseBO extends SuperBO {

    List<CourseDTO> getAllCourses() throws Exception;

    boolean saveCourse(CourseDTO courseDTO) throws Exception;

    boolean updateCourse(CourseDTO courseDTO) throws Exception;

    boolean deleteCourse(String id) throws Exception;

    List<String> getAllCoursesIds() throws Exception;

    Optional<CourseDTO> findCourseById(String id) throws Exception;

    String generateNewCourseId() throws Exception;

}
