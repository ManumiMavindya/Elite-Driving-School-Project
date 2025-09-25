package lk.ijse.elitedrivingschoolproject.dto.tm;

import javafx.scene.layout.Pane;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data

public class CourseTM {
    private String courseId;
    private String courseName;
    private String duration;
    private double fee;
    private String description;
    private Pane action;
}
