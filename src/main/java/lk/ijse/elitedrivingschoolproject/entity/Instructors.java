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
@Table(name = "instructors")
public class Instructors {

    @Id
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

    @ManyToOne
    @JoinColumn
    private Course course_id;

    @Column(nullable = false,unique = true)
    private String status;

    @OneToMany(
            mappedBy = "instructors",
            cascade = CascadeType.ALL
    )
    private List<Lessons> lessons;

}
