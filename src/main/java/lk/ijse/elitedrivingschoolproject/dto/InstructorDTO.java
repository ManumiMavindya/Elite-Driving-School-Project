package lk.ijse.elitedrivingschoolproject.dto;

import lombok.*;

import java.time.LocalDate;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString

public class InstructorDTO {
    private String instructorId;
    private String instructorFullName;
    private LocalDate instructorBirthDate;
    private String instructorPhone;
    private String instructorAddress;
    private String instructorEmail;
    private String instructorLicenceNumber;
    private String courseName;
    private String status;

}

