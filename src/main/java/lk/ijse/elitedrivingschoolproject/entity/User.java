package lk.ijse.elitedrivingschoolproject.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter

@Entity
@Table(name = "user")
public class User {

    @Id
    @Column(unique = true, nullable = false)
    private String user_id;

    @Column(nullable = false)
    private String user_name;

    @Column(nullable = false)
    private String age;

    @Column(nullable = false, unique = true)
    private String email;

    @Column(nullable = false)
    private String password;

    @Column(nullable = false, unique = true)
    private String contactNumber;

    @Column(nullable = false)
    private String action;
    
}
