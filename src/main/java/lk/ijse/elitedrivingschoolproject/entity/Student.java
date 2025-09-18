package lk.ijse.elitedrivingschoolproject.entity;

import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDate;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter

@Entity
@Table(name = "student")
public class Student {

    @Id
    @Column
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private String student_id;

    @Column(nullable = false)
    private String student_name;

    @Column(nullable = false)
    private LocalDate student_birth_date;

    @Column(nullable = false)
    private String student_gender;

    @Column(nullable = false, unique = true)
    private String student_phone;

    @Column(nullable = false)
    private String student_address;

    @Column(nullable = false, unique = true)
    private String student_email;

    @Column(nullable = false, unique = true)
    private LocalDate registration_date;
}
