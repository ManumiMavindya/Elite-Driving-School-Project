package lk.ijse.elitedrivingschoolproject.dto.tm;

import javafx.scene.layout.Pane;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data

public class UserTM {

    private String userId;
    private String username;
    private int age;
    private String email;
    private String password;
    private String contactNumber;
    private String role;
    private Pane action;

}
