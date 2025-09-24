package lk.ijse.elitedrivingschoolproject.bo.custom;

import lk.ijse.elitedrivingschoolproject.dto.StudentCourseDetailsDTO;

import java.util.List;
import java.util.Optional;

public interface StudentCourseDetailBO {

    List<StudentCourseDetailsDTO> getAllStudentCourseDetails() throws Exception;

    String getLastStudentCourseDetailId() throws Exception;

    boolean saveStudentCourseDetails(StudentCourseDetailsDTO t) throws Exception;

    boolean updateStudentCourseDetails(StudentCourseDetailsDTO t) throws Exception;

    boolean deleteStudentCourseDetails(String id) throws Exception;

    List<String> getAllStudentCourseDetailIds() throws Exception;

    Optional<StudentCourseDetailsDTO> findByStudentCourseDetailId(String id) throws Exception;
}
