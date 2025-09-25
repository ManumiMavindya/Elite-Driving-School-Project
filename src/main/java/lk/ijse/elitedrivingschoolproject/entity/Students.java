package lk.ijse.elitedrivingschoolproject.entity;

import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDate;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString

@Entity
@Table(name = "students")
public class Students {

    @Id
    @Column
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
    private String nationalId;

    //add course

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinTable(
            name = "student_course",
            joinColumns = @JoinColumn(name = "student_id"),
            inverseJoinColumns = @JoinColumn(name = "course_id")

    )
    @ToString.Exclude
    private List<Course> courses;

    //
    @OneToMany(
            mappedBy = "students",
            cascade = CascadeType.ALL
    )
    @ToString.Exclude
    private List<Lessons> lessons;

    @OneToMany(
            mappedBy = "students",
            cascade = CascadeType.ALL
    )
    @ToString.Exclude
    private List<Payments> payments;

    @OneToMany(
            mappedBy = "students",
            cascade = CascadeType.ALL,
            fetch = FetchType.EAGER
    )
    @ToString.Exclude
    private List<StudentCourseDetails> studentCourseDetails;

}
