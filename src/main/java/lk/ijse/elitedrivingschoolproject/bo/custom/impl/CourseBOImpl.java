package lk.ijse.elitedrivingschoolproject.bo.custom.impl;

import lk.ijse.elitedrivingschoolproject.bo.custom.CourseBO;
import lk.ijse.elitedrivingschoolproject.dto.CourseDTO;

import java.util.List;
import java.util.Optional;

public class CourseBOImpl implements CourseBO {
    @Override
    public List<CourseDTO> getAllCourses() throws Exception {

    }

    @Override
    public boolean saveCourse(CourseDTO courseDTO) throws Exception {
        return false;
    }

    @Override
    public boolean updateCourse(CourseDTO courseDTO) throws Exception {
        return false;
    }

    @Override
    public boolean deleteCourse(String id) throws Exception {
        return false;
    }

    @Override
    public List<String> getAllCoursesIds() throws Exception {
        return List.of();
    }

    @Override
    public Optional<CourseDTO> findCourseById(String id) throws Exception {
        return Optional.empty();
    }
}
