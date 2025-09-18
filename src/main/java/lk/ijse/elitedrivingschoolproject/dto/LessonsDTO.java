package lk.ijse.elitedrivingschoolproject.dto;

import java.sql.Time;
import java.time.LocalDate;


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
