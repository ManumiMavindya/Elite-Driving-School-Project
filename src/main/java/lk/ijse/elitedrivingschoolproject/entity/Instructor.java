package lk.ijse.elitedrivingschoolproject.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter

@Entity
@Table(name = "instructor")
public class Instructor {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    private String instructor_id;

    @Column(nullable = false)
    private String instructor_name;

    @Column(nullable = false)
    private LocalDate instructor_birth_date;

    @Column(nullable = false,unique = true)
    private String instructor_phone;

    @Column(nullable = false)
    private String instructor_address;

    @Column(nullable = false,unique = true)
    private String instructor_email;

    @Column(nullable = false,unique = true)
    private String licence_number;

    @Column(nullable = false)
    private String course_name;

    @Column(nullable = false,unique = true)
    private String status;

}
