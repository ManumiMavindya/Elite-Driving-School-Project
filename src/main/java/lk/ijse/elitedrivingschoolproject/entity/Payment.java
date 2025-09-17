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
public class Payment {

    @Id
    @Column
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private String transaction_Id;

    @Column(nullable = false)
    private String student_Name;

    @Column(nullable = false)
    private String course_Name;

    @Column(nullable = false)
    private LocalDate payment_Date;

    @Column(nullable = false)
    private int payment_Amount;

    @Column(nullable = false)
    private String payment_status;

    @Column(nullable = false)
    private String payment_action;
}
