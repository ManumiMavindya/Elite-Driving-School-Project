package lk.ijse.elitedrivingschoolproject.entity;

import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDate;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter

@Entity
@Table(name = "payment")
public class Payments {

    @Id
    @Column
    private String transaction_Id;

    @ManyToOne
    @JoinColumn
    private Students student_id;

    @ManyToOne
    @JoinColumn
    private Course course_Id;

    @Column(nullable = false)
    private LocalDate payment_Date;

    @Column(nullable = false)
    private double payment_Amount;

    @Column(nullable = false)
    private String payment_status;

    @Column(nullable = false)
    private String payment_action;
}
