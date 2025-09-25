package lk.ijse.elitedrivingschoolproject.dto.tm;

import javafx.scene.layout.Pane;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Time;
import java.time.LocalDate;

@AllArgsConstructor
@NoArgsConstructor
@Data

public class LessonsTM {
    private String lessonId;
    private String studentId;
    private String courseId;
    private String instructorId;
    private LocalDate lessonDate;
    private Time startTime;
    private Time endTime;
    private String status;
    private Pane action;
}
