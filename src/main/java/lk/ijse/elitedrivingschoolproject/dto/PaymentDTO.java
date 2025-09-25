package lk.ijse.elitedrivingschoolproject.dto;

import lombok.*;

import java.time.LocalDate;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
@Builder

public class PaymentDTO {
    private String transactionId;
    private String studentId;
    private String courseId;
    private LocalDate paymentDate;
    private int paymentAmount;
    private String paymentStatus;
}
