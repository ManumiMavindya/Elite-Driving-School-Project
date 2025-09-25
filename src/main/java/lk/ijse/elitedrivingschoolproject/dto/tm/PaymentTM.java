package lk.ijse.elitedrivingschoolproject.dto.tm;

import javafx.scene.control.Button;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@AllArgsConstructor
@NoArgsConstructor
@Data

public class PaymentTM {
    private String transactionId;
    private String studentId;
    private String courseId;
    private LocalDate paymentDate;
    private int paymentAmount;
    private String paymentStatus;
    private Button updateButton;
    private Button deleteButton;
}
