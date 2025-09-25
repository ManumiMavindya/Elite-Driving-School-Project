package lk.ijse.elitedrivingschoolproject.dto;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Data
@Builder

public class CourseDTO {

    private String courseId;
    private String courseName;
    private String duration;
    private double fee;
    private String description;

}
