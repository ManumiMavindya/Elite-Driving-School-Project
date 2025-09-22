package lk.ijse.elitedrivingschoolproject.bo.custom;

import lk.ijse.elitedrivingschoolproject.dto.StudentDTO;

import java.util.List;
import java.util.Optional;

public interface StudentBO {

    public List<StudentDTO> getAllStudents() throws Exception;

    public String getLastStudentId() throws Exception;

    public boolean saveStudent(StudentDTO student) throws Exception;

    public boolean updateStudent(StudentDTO student) throws Exception;

    public boolean deleteStudent(String id) throws Exception;

    public List<String> getAllStudentIds() throws Exception;

    public Optional<StudentDTO> findByStudentId(String id) throws Exception;

    public String gennerateNewStudentId() throws Exception;

}
