package lk.ijse.elitedrivingschoolproject.dto;


import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Data

public class UserDTO {
    private String userId;
    private String username;
    private int age;
    private String email;
    private String password;
    private String contactNumber;
}