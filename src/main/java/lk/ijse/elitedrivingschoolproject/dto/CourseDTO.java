package lk.ijse.elitedrivingschoolproject.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter

public class CourseDTO {

    private String courseId;
    private String courseName;
    private String duration;
    private double fee;
    private String description;

}
