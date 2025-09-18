package lk.ijse.elitedrivingschoolproject.bo.custom.impl;

import lk.ijse.elitedrivingschoolproject.bo.custom.InstructorBO;
import lk.ijse.elitedrivingschoolproject.bo.util.EntityDTOConverter;
import lk.ijse.elitedrivingschoolproject.dao.DAOFactory;
import lk.ijse.elitedrivingschoolproject.dao.DAOTypes;
import lk.ijse.elitedrivingschoolproject.dao.custom.InstructorDAO;
import lk.ijse.elitedrivingschoolproject.dto.InstructorDTO;
import lk.ijse.elitedrivingschoolproject.entity.Instructors;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class InstructorBOImpl implements InstructorBO {

    private final InstructorDAO instructorDAO = DAOFactory.getInstance().getDAO(DAOTypes.INSTRUCTORS);
    private final EntityDTOConverter entityDTOConverter = new EntityDTOConverter();


    @Override
    public List<InstructorDTO> getAllInstructors() throws Exception {

        List<Instructors> instructors = instructorDAO.getAll();
        List<InstructorDTO> instructorDTOs = new ArrayList<>();

        for (Instructors instructor : instructors) {
            instructorDTOs.add(entityDTOConverter.getInstructorDTO(instructor));
        }
        return instructorDTOs;
    }

    @Override
    public boolean saveInstructor(InstructorDTO instructorDTO) throws Exception {

        Optional<Instructors> instructors = instructorDAO.findById(instructorDTO.getInstructorId());

        if (instructors.isPresent()) {
            throw new Exception("Instructor already exists");
        }
        return instructorDAO.save(entityDTOConverter.getInstructorEntity(instructorDTO));
    }

    @Override
    public boolean updateInstructor(InstructorDTO instructorDTO) throws Exception {

        Optional<Instructors> instructors =instructorDAO.findById(instructorDTO.getInstructorId());

        if (instructors.isPresent()) {
            throw new Exception("Instructor Not Found");
        }
        return instructorDAO.update(entityDTOConverter.getInstructorEntity(instructorDTO));
    }

    @Override
    public boolean deleteInstructor(String id) throws Exception {

        Optional<Instructors> instructors = instructorDAO.findById(id);

        if (instructors.isEmpty()) {
            throw new Exception("Instructor Not Found");
        }
        return instructorDAO.delete(id);
    }

    @Override
    public List<String> getAllInstrucrorsIds() throws Exception {
        return instructorDAO.getAllIds();
    }

    @Override
    public Optional<InstructorDTO> findByInstructorId(String id) throws Exception {

        Optional<Instructors> instructors = instructorDAO.findById(id);

        if (instructors.isPresent()) {
            return Optional.of(entityDTOConverter.getInstructorDTO(instructors.get()));
        }
        return Optional.empty();
    }

}
