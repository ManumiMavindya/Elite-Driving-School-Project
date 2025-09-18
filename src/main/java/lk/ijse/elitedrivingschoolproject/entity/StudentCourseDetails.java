package lk.ijse.elitedrivingschoolproject.entity;

import java.time.LocalDate;

import jakarta.persistence.*;
import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter

@Entity
@Table(name = "student_course_details")
public class StudentCourseDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    private String studentCourseId;

    @ManyToOne
    @JoinColumn
    private Students student_id;

    @ManyToOne
    @JoinColumn
    private Course course_id;

    @Column(nullable = false)
    private LocalDate enrollment_Date;

    @Column(nullable = false)
    private String status;
}
