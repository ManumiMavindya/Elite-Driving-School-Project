package lk.ijse.elitedrivingschoolproject.dto;

import java.sql.Time;
import java.time.LocalDate;
import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString

public class LessonsDTO {

    private String lessonId;
    private String studentId;
    private String courseId;
    private String instructorId;
    private LocalDate lessonDate;
    private Time startTime;
    private Time endTime;
    private String status;

}
