package lk.ijse.elitedrivingschoolproject.dto;

import lombok.*;

import java.time.LocalDate;
import java.util.List;


@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
@Builder

public class StudentDTO {

    private String studentId;
    private String studentFullName;
    private LocalDate studentBirthDate;
    private String studentGender;
    private String studentPhone;
    private String studentAddress;
    private String studentEmail;
    private String NationalId;
    private List<CourseDTO> courses;


}
