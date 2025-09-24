package lk.ijse.elitedrivingschoolproject.bo.custom.impl;

import lk.ijse.elitedrivingschoolproject.bo.custom.StudentCourseDetailBO;
import lk.ijse.elitedrivingschoolproject.bo.exception.DuplicateException;
import lk.ijse.elitedrivingschoolproject.bo.util.EntityDTOConverter;
import lk.ijse.elitedrivingschoolproject.dao.DAOFactory;
import lk.ijse.elitedrivingschoolproject.dao.DAOTypes;
import lk.ijse.elitedrivingschoolproject.dao.custom.CourseDAO;
import lk.ijse.elitedrivingschoolproject.dao.custom.StudentCourseDetailsDAO;
import lk.ijse.elitedrivingschoolproject.dao.custom.StudentDAO;
import lk.ijse.elitedrivingschoolproject.dto.StudentCourseDetailsDTO;
import lk.ijse.elitedrivingschoolproject.entity.Course;
import lk.ijse.elitedrivingschoolproject.entity.StudentCourseDetails;
import lk.ijse.elitedrivingschoolproject.entity.Students;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class StudentCourseDetailBOImpl implements StudentCourseDetailBO {

    private final StudentCourseDetailsDAO studentCourseDetailsDAO = (StudentCourseDetailsDAO) DAOFactory.getInstance().getDAO(DAOTypes.STUDENT_COURSE_DETAILS);
    private final StudentDAO studentDAO = (StudentDAO) DAOFactory.getInstance().getDAO(DAOTypes.STUDENTS);
    private final CourseDAO courseDAO = (CourseDAO) DAOFactory.getInstance().getDAO(DAOTypes.COURSE);
    private final EntityDTOConverter converter = new EntityDTOConverter();


    @Override
    public List<StudentCourseDetailsDTO> getAllStudentCourseDetails() throws Exception {

        List<StudentCourseDetails> studentCourseDetails = studentCourseDetailsDAO.getAll();
        List<StudentCourseDetailsDTO> studentCourseDetailsDTOs = new ArrayList<>();
        for (StudentCourseDetails studentCourseDetail : studentCourseDetails) {
            studentCourseDetailsDTOs.add(converter.getStudentCourseDetailsDTO(studentCourseDetail));
        }
        return studentCourseDetailsDTOs;
    }

    @Override
    public String getLastStudentCourseDetailId() throws Exception {

        return studentCourseDetailsDAO.getLastId();
    }

    @Override
    public boolean saveStudentCourseDetails(StudentCourseDetailsDTO t) throws Exception {

        Optional<Students> studentExists = studentDAO.findById(t.getStudentId());
        Optional<Course> courseExists = courseDAO.findById(t.getCourseId());
        Optional<StudentCourseDetails> studentCourseDetailsExists = studentCourseDetailsDAO.findById(t.getStudentCourseId());

        if (studentCourseDetailsExists.isPresent()) {
            throw new DuplicateException("Student Course Details already exists");
        }
        if (studentExists.isPresent() &&  courseExists.isPresent()) {
            return studentCourseDetailsDAO.save(converter.getStudentCourseDetailsEntity(t));
        }
        throw new Exception("Student or Course not found");
    }

    @Override
    public boolean updateStudentCourseDetails(StudentCourseDetailsDTO t) throws Exception {

        Optional<StudentCourseDetails>  studentCourseDetailsExists = studentCourseDetailsDAO.findById(t.getStudentCourseId());
        if (studentCourseDetailsExists.isEmpty()) {
            throw new Exception("Student Course not found");
        }
        return studentCourseDetailsDAO.update(converter.getStudentCourseDetailsEntity(t));
    }

    @Override
    public boolean deleteStudentCourseDetails(String id) throws Exception {

        Optional<StudentCourseDetails>  studentCourseDetailsExists = studentCourseDetailsDAO.findById(id);
        if (studentCourseDetailsExists.isEmpty()) {
            throw new Exception("Student Course not found");
        }
        return studentCourseDetailsDAO.delete(id);

    }

    @Override
    public List<String> getAllStudentCourseDetailIds() throws Exception {

        return studentCourseDetailsDAO.getAllIds();
    }

    @Override
    public Optional<StudentCourseDetailsDTO> findByStudentCourseDetailId(String id) throws Exception {

        Optional<StudentCourseDetails> details = studentCourseDetailsDAO.findById(id);
        if (details.isPresent()) {
            return Optional.of(converter.getStudentCourseDetailsDTO(details.get()));
        } else {
            return Optional.empty();
        }
    }

}
