package lk.ijse.elitedrivingschoolproject.dto.tm;

import javafx.scene.control.Button;

import java.time.LocalDate;

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
