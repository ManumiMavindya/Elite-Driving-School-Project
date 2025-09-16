package lk.ijse.elitedrivingschoolproject.dto;

import lombok.*;

import java.time.LocalDate;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString

public class PaymentDTO {
    private String transactionId;
    private String studentName;
    private String courseName;
    private LocalDate paymentDate;
    private int paymentAmount;
    private String paymentStatus;
    private String action;
}
