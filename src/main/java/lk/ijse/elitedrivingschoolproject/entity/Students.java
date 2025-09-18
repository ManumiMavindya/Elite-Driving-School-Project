package lk.ijse.elitedrivingschoolproject.entity;

import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDate;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter

@Entity
@Table(name = "students")
public class Students {

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

    @OneToMany(
            mappedBy = "students",
            cascade = CascadeType.ALL
    )
    private List<Lessons> lessons;

    @OneToMany(
            mappedBy = "students",
            cascade = CascadeType.ALL
    )
    private List<Payments> payments;

    @ManyToMany(
            mappedBy = "students",
            cascade = CascadeType.ALL
    )
    private List<StudentCourseDetails> studentCourseDetails;

}
