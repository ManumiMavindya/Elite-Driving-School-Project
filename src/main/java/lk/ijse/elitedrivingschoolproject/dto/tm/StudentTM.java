package lk.ijse.elitedrivingschoolproject.dto.tm;

import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.layout.Pane;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@AllArgsConstructor
@NoArgsConstructor
@Data


public class StudentTM {
    private String studentId;
    private String studentFullName;
    private LocalDate studentBirthDate;
    private String studentGender;
    private String studentPhone;
    private String studentAddress;
    private String studentEmail;
    private String studentNationalId;
    private CheckBox studentCourseId;
    private Pane action;


    public StudentTM(String studentId, String studentFullName, LocalDate studentBirthDate, String studentGender, String studentPhone, String studentAddress, String studentEmail, String nationalId, String selectedCourseNamesString, Pane action) {
        this.studentId = studentId;
        this.studentFullName = studentFullName;
        this.studentBirthDate = studentBirthDate;
        this.studentGender = studentGender;
        this.studentPhone = studentPhone;
        this.studentAddress = studentAddress;
        this.studentEmail = studentEmail;
        this.studentNationalId = nationalId;

    }
}
