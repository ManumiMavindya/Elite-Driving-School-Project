package lk.ijse.elitedrivingschoolproject.bo.custom.impl;

import lk.ijse.elitedrivingschoolproject.bo.custom.StudentBO;
import lk.ijse.elitedrivingschoolproject.bo.exception.DuplicateException;
import lk.ijse.elitedrivingschoolproject.bo.util.EntityDTOConverter;
import lk.ijse.elitedrivingschoolproject.dao.DAOFactory;
import lk.ijse.elitedrivingschoolproject.dao.DAOTypes;
import lk.ijse.elitedrivingschoolproject.dao.custom.StudentDAO;
import lk.ijse.elitedrivingschoolproject.dto.StudentDTO;
import lk.ijse.elitedrivingschoolproject.entity.Students;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class StudentBOImpl implements StudentBO {

    private final StudentDAO studentDAO = DAOFactory.getInstance().getDAO(DAOTypes.STUDENTS);
    private final EntityDTOConverter entityDTOConverter = new EntityDTOConverter();


    @Override
    public List<StudentDTO> getAllStudents() throws Exception {

        List<Students> students = studentDAO.getAll();
        List<StudentDTO> studentDTOList = new ArrayList<>();
        for (Students student: students) {
            studentDTOList.add(entityDTOConverter.getStudentDTO(student));
        }
        return studentDTOList;
    }

    @Override
    public String getLastStudentId() throws Exception {

        return studentDAO.getLastId();
    }

    @Override
    public boolean saveStudent(StudentDTO student) throws Exception {

        Optional<Students> students = studentDAO.findById(student.getStudentId());
        if (students.isPresent()) {
            throw new DuplicateException("Student already exists");
        }
        return studentDAO.save(entityDTOConverter.getStudentEntity(student));
    }

    @Override
    public boolean updateStudent(StudentDTO student) throws Exception {

        Optional<Students> students = studentDAO.findById(student.getStudentId());
        if (students.isEmpty()) {
            throw new DuplicateException("Student already exists");
        }
        return studentDAO.save(entityDTOConverter.getStudentEntity(student));
    }

    @Override
    public boolean deleteStudent(String id) throws Exception {

        Optional<Students> students = studentDAO.findById(id);
        if (students.isEmpty()) {
            throw new DuplicateException("Student not found");
        }
        return studentDAO.delete(id);
    }

    @Override
    public List<String> getAllStudentIds() throws Exception {

        return studentDAO.getAllIds();
    }

    @Override
    public Optional<StudentDTO> findByStudentId(String id) throws Exception {

        Optional<Students> students = studentDAO.findById(id);
        if (students.isEmpty()) {
            return Optional.empty();
        }
        return Optional.of(entityDTOConverter.getStudentDTO(students.get()));
    }

    @Override
    public String gennerateNewStudentId() throws Exception {

        return studentDAO.generateNewId();
    }

}
