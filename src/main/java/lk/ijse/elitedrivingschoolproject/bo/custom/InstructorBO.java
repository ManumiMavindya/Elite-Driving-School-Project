package lk.ijse.elitedrivingschoolproject.bo.custom;

import lk.ijse.elitedrivingschoolproject.bo.SuperBO;
import lk.ijse.elitedrivingschoolproject.dto.InstructorDTO;

import java.util.List;
import java.util.Optional;

public interface InstructorBO extends SuperBO {

    List<InstructorDTO> getAllInstructors() throws Exception;

    boolean saveInstructor(InstructorDTO instructorDTO) throws Exception;

    boolean updateInstructor(InstructorDTO instructorDTO) throws Exception;

    boolean deleteInstructor(String id) throws Exception;

    List<String> getAllInstrucrorsIds() throws Exception;

    Optional<InstructorDTO> findByInstructorId(String id) throws Exception;

}
