package lk.ijse.elitedrivingschoolproject.dto.tm;



import java.time.LocalDate;

import javafx.scene.layout.Pane;
import lombok.*;


@AllArgsConstructor
@NoArgsConstructor
@Data

public class InstructorTM {
    private String instructorId;
    private String instructorFullName;
    private LocalDate instructorBirthDate;
    private String instructorPhone;
    private String instructorAddress;
    private String instructorEmail;
    private String instructorLicenceNumber;
    private String courseId;
    private String status;
    private Pane action;
}
