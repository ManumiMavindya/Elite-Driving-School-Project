package lk.ijse.elitedrivingschoolproject.dto;

import java.time.LocalDate;
import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter


public class StudentCourseDetailsDTO {
    private String studentCourseId;
    private String studentId;
    private String courseId;
    private LocalDate enrollmentDate;
    private String status;
}
