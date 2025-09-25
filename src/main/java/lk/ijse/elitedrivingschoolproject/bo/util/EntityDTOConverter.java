package lk.ijse.elitedrivingschoolproject.bo.util;

import lk.ijse.elitedrivingschoolproject.dto.*;
import lk.ijse.elitedrivingschoolproject.entity.*;

import java.util.stream.Collectors;

public class EntityDTOConverter {

    public CourseDTO getCourseDTO(Course course) {
        CourseDTO dto = new CourseDTO();

        dto.setCourseId(course.getCourse_id());
        dto.setCourseName(course.getCourse_name());
        dto.setDuration(course.getCourse_duration());
        dto.setFee(Double.parseDouble(course.getCourse_fee()));
        dto.setDescription(course.getCourse_description());
        return dto;
    }

    public Course getCourseEntity(CourseDTO dto) {
        Course course = new Course();

        course.setCourse_id(dto.getCourseId());
        course.setCourse_name(dto.getCourseName());
        course.setCourse_duration(dto.getDuration());
        course.setCourse_fee(String.valueOf(dto.getFee()));
        course.setCourse_description(dto.getDescription());
        return course;
    }

    public InstructorDTO getInstructorDTO(Instructors instructors) {
        InstructorDTO dto = new InstructorDTO();

        dto.setInstructorId(instructors.getInstructor_id());
        dto.setInstructorFullName(instructors.getInstructor_name());
        dto.setInstructorBirthDate(instructors.getInstructor_birth_date());
        dto.setInstructorPhone(instructors.getInstructor_phone());
        dto.setInstructorAddress(instructors.getInstructor_address());
        dto.setInstructorEmail(instructors.getInstructor_email());
        dto.setInstructorLicenceNumber(instructors.getLicence_number());
        dto.setCourseId(instructors.getCourse_id().getCourse_id());
        dto.setStatus(instructors.getStatus());
        return dto;
    }

    public Instructors getInstructorEntity(InstructorDTO dto) {
        Instructors instructors = new Instructors();
        Course course = new Course();

        instructors.setInstructor_id(dto.getInstructorId());
        instructors.setInstructor_name(dto.getInstructorFullName());
        instructors.setInstructor_birth_date(dto.getInstructorBirthDate());
        instructors.setInstructor_phone(dto.getInstructorPhone());
        instructors.setInstructor_address(dto.getInstructorAddress());
        instructors.setInstructor_email(dto.getInstructorEmail());
        instructors.setLicence_number(dto.getInstructorLicenceNumber());
        course.setCourse_id(dto.getCourseId());
        instructors.setCourse_id(course);
        instructors.setStatus(dto.getStatus());
        return instructors;
    }

    public LessonsDTO getLessonsDTO(Lessons lessons) {
        LessonsDTO dto = new LessonsDTO();

        dto.setLessonId(lessons.getLesson_id());
        dto.setStudentId(lessons.getStudent_id().getStudent_id());
        dto.setCourseId(lessons.getCourse_id().getCourse_id());
        dto.setInstructorId(lessons.getInstructor_id().getInstructor_id());
        dto.setLessonDate(lessons.getLesson_Date());
        dto.setStartTime(lessons.getStart_Time());
        dto.setEndTime(lessons.getEnd_Time());
        dto.setStatus(lessons.getStatus());
        return dto;

    }

    public Lessons getLessonsEntity(LessonsDTO dto) {
        Lessons lessons = new Lessons();
        Instructors instructors = new Instructors();
        Course course = new Course();
        Students student = new Students();

        lessons.setLesson_id(dto.getLessonId());
        student.setStudent_id(dto.getStudentId());
        lessons.setStudent_id(student);
        course.setCourse_id(dto.getCourseId());
        lessons.setCourse_id(course);
        instructors.setInstructor_id(dto.getInstructorId());
        lessons.setInstructor_id(instructors);
        lessons.setLesson_Date(dto.getLessonDate());
        lessons.setStart_Time(dto.getStartTime());
        lessons.setEnd_Time(dto.getEndTime());
        lessons.setStatus(dto.getStatus());
        return lessons;

    }

    public PaymentDTO getPaymentDTO(Payments payments) {
        PaymentDTO dto = new PaymentDTO();

        dto.setTransactionId(payments.getTransaction_Id());
        dto.setStudentId(payments.getStudent_id().getStudent_id());
        dto.setCourseId(payments.getCourse_Id().getCourse_id());
        dto.setPaymentDate(payments.getPayment_Date());
        dto.setPaymentAmount(payments.getPayment_Amount());
        dto.setPaymentStatus(payments.getPayment_status());
        return dto;
    }

    public Payments getPaymentEntity(PaymentDTO dto) {
        Payments payments = new Payments();
        Course course = new Course();
        Students student = new Students();

        payments.setTransaction_Id(dto.getTransactionId());
        student.setStudent_id(dto.getStudentId());
        payments.setStudent_id(student);
        course.setCourse_id(dto.getCourseId());
        payments.setCourse_Id(course);
        payments.setPayment_Date(dto.getPaymentDate());
        payments.setPayment_Amount(dto.getPaymentAmount());
        payments.setPayment_status(dto.getPaymentStatus());
        return payments;

    }

    public StudentCourseDetailsDTO getStudentCourseDetailsDTO(StudentCourseDetails studentCourseDetails) {
        StudentCourseDetailsDTO dto = new StudentCourseDetailsDTO();

        dto.setStudentCourseId(studentCourseDetails.getStudentCourseId());
        dto.setStudentId(studentCourseDetails.getStudent_id().getStudent_id());
        dto.setCourseId(studentCourseDetails.getCourse_id().getCourse_id());
        dto.setEnrollmentDate(studentCourseDetails.getEnrollment_Date());
        dto.setStatus(studentCourseDetails.getStatus());
        return dto;

    }

    public StudentCourseDetails getStudentCourseDetailsEntity(StudentCourseDetailsDTO dto) {
        StudentCourseDetails studentCourseDetails = new StudentCourseDetails();
        Students students = new Students();
        Course course = new Course();

        studentCourseDetails.setStudentCourseId(dto.getStudentCourseId());
        students.setStudent_id(dto.getStudentId());
        studentCourseDetails.setStudent_id(students);
        course.setCourse_id(dto.getCourseId());
        studentCourseDetails.setCourse_id(course);
        studentCourseDetails.setEnrollment_Date(dto.getEnrollmentDate());
        studentCourseDetails.setStatus(dto.getStatus());
        return studentCourseDetails;

    }

    public StudentDTO getStudentDTO(Students students) {
        StudentDTO dto = new StudentDTO();

        dto.setStudentId(students.getStudent_id());
        dto.setStudentFullName(students.getStudent_name());
        dto.setStudentBirthDate(students.getStudent_birth_date());
        dto.setStudentGender(students.getStudent_gender());
        dto.setStudentPhone(students.getStudent_phone());
        dto.setStudentAddress(students.getStudent_address());
        dto.setStudentEmail(students.getStudent_email());
        dto.setNationalId(students.getNationalId());
        dto.setCourses(
                students.getCourses()
                        .stream().map(this::getCourseDTO).toList()

        );
        return dto;
    }

    public Students getStudentEntity(StudentDTO dto) {
        Students students = new Students();

        students.setStudent_id(dto.getStudentId());
        students.setStudent_name(dto.getStudentFullName());
        students.setStudent_birth_date(dto.getStudentBirthDate());
        students.setStudent_gender(dto.getStudentGender());
        students.setStudent_phone(dto.getStudentPhone());
        students.setStudent_address(dto.getStudentAddress());
        students.setStudent_email(dto.getStudentEmail());
        students.setNationalId(dto.getNationalId());
        students.setCourses(
                dto.getCourses()
                        .stream().map(this::getCourseEntity).toList()
        );
        return students;
    }

    //user

    public UserDTO getUserDTO(User user) {
        if (user == null) {
            return null;
        }
        UserDTO dto = new UserDTO();
        dto.setUserId(user.getUser_id());
        dto.setUsername(user.getUser_name());
        dto.setAge(Integer.parseInt(user.getAge()));
        dto.setEmail(user.getEmail());
        dto.setPassword(user.getPassword());
        dto.setContactNumber(user.getContactNumber());
        return dto;
    }

    public User getUserEntity(UserDTO dto) {
        User user = new User();

        user.setUser_id(dto.getUserId());
        user.setUser_name(dto.getUsername());
        user.setAge(String.valueOf(dto.getAge()));
        user.setEmail(dto.getEmail());
        user.setPassword(dto.getPassword());
        user.setContactNumber(dto.getContactNumber());
        return user;
    }

}
