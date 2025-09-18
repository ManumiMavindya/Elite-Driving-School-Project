package lk.ijse.elitedrivingschoolproject.entity;

import jakarta.persistence.*;

import java.sql.Time;
import java.time.LocalDate;
import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter


@Entity
@Table(name = "lessons")
public class Lessons {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    private String lesson_id;

    @ManyToOne
    @JoinColumn
    private Students student_id;

    @ManyToOne
    @JoinColumn
    private Course course_id;

    @ManyToOne
    @JoinColumn
    private Instructors instructor_id;

    @Column(nullable = false)
    private LocalDate lesson_Date;

    @Column(nullable = false)
    private Time start_Time;

    @Column(nullable = false)
    private Time end_Time;

    @Column(nullable = false)
    private String status;
}
