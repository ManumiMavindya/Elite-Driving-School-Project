package lk.ijse.elitedrivingschoolproject.dto;

import lombok.*;

import java.time.LocalDate;


@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class StudentDTO {

    private String studentId;
    private String studentFullName;
    private LocalDate studentBirthDate;
    private String studentGender;
    private String studentPhone;
    private String studentAddress;
    private String studentEmail;
    private LocalDate registrationDate;

}
