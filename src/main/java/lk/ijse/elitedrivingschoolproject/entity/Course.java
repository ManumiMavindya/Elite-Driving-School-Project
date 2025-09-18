package lk.ijse.elitedrivingschoolproject.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter

@Entity
@Table(name ="course")
public class Course {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    private String course_id;

    @Column(nullable = false)
    private String course_name;

    @Column(nullable = false)
    private String course_duration;

    @Column(nullable = false)
    private String course_fee;

    @Column(nullable = false)
    private String course_description;

    @OneToMany(
            mappedBy = "course",
            cascade = CascadeType.ALL
    )
    private List<Lessons> lessons;

    @OneToMany(
            mappedBy = "course",
            cascade = CascadeType.ALL
    )
    private List<StudentCourseDetails> studentCourseDetails;

    @OneToMany(
            mappedBy = "course",
            cascade = CascadeType.ALL
    )
    private List<Instructors> instructors;

    @OneToMany(
            mappedBy = "course",
            cascade = CascadeType.ALL
    )
    private List<Payments> payments;
}
