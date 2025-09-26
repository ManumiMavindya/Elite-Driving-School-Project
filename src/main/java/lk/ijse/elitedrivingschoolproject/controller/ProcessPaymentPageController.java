package lk.ijse.elitedrivingschoolproject.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import lk.ijse.elitedrivingschoolproject.bo.BOFactory;
import lk.ijse.elitedrivingschoolproject.bo.BOTypes;
import lk.ijse.elitedrivingschoolproject.bo.custom.LessonsBO;
import lk.ijse.elitedrivingschoolproject.bo.custom.PaymentBO;
import lk.ijse.elitedrivingschoolproject.dto.InstructorDTO;
import lk.ijse.elitedrivingschoolproject.dto.PaymentDTO;

import java.net.URL;
import java.time.LocalDate;
import java.util.ResourceBundle;

public class ProcessPaymentPageController implements Initializable {

    @FXML
    private TextField amounttxt;

    @FXML
    private Button completeProcessPaymentbtn;

    @FXML
    private ComboBox<String> coursecmb;

    @FXML
    private DatePicker datepicker;

    @FXML
    private ComboBox<String> studentcmb;

    @FXML
    private Label transactionIdlbl;

    @FXML
    private ComboBox<String> statuscmb;

    @FXML
    private Button updatePaymentbtn;

    PaymentBO paymentBO = (PaymentBO) BOFactory.getInstance().getBO(BOTypes.PAYMENTS) ;

    @FXML
    void completeProcessPaymentbtnOnAction(ActionEvent event) {

        String studentId = studentcmb.getValue();
        String courseId = coursecmb.getValue();
        LocalDate date = datepicker.getValue();
        Double amount = Double.parseDouble(amounttxt.getText());
        String status = statuscmb.getValue();

        if (studentId != null || courseId != null || date != null || amount != null || status != null) {
            completeProcessPaymentbtn.setDisable(true);
            new Alert(Alert.AlertType.ERROR,"pleas enter all the fields",ButtonType.OK).show();
            return;
        }

        try {
            boolean isSaved = paymentBO.savePayment(PaymentDTO.builder()
                            .transactionId(transactionIdlbl.getText())
                            .studentId(studentId)
                            .courseId(courseId)
                            .paymentDate(date)
                            .paymentAmount(amount)
                            .paymentStatus(status)
                    .build());

            if (isSaved) {
                new Alert(Alert.AlertType.INFORMATION,"Payment saved successfully").show();
            }else {
                new Alert(Alert.AlertType.ERROR,"Payment not saved successfully").show();
            }
        }catch (Exception e) {
            throw new RuntimeException(e);
        }

    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        try {
            transactionIdlbl.setText(paymentBO.generateNewPaymentId());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public void loadData(PaymentDTO paymentDTO) throws Exception {

        transactionIdlbl.setText(paymentDTO.getTransactionId());
        studentcmb.setValue(paymentDTO.getStudentId());
        coursecmb.setValue(paymentDTO.getCourseId());
        datepicker.setValue(paymentDTO.getPaymentDate());
        amounttxt.setText(String.valueOf(paymentDTO.getPaymentAmount()));
        statuscmb.setValue(paymentDTO.getPaymentStatus());

    }

    public void updatePaymentbtnOnAction(ActionEvent actionEvent) {

        String studentId = studentcmb.getValue();
        String courseId = coursecmb.getValue();
        LocalDate date = datepicker.getValue();
        Double amount = Double.parseDouble(amounttxt.getText());
        String status = statuscmb.getValue();

        if (studentId != null || courseId != null || date != null || amount != null || status != null) {
            updatePaymentbtn.setDisable(true);
            new Alert(Alert.AlertType.ERROR,"pleas enter all the fields",ButtonType.OK).show();
            return;
        }

        try {
            boolean isUpdated = paymentBO.updatePayment(PaymentDTO.builder()
                    .transactionId(transactionIdlbl.getText())
                    .studentId(studentId)
                    .courseId(courseId)
                    .paymentDate(date)
                    .paymentAmount(amount)
                    .paymentStatus(status)
                    .build());

            if (isUpdated) {
                new Alert(Alert.AlertType.INFORMATION,"Payment updated successfully").show();
            }else {
                new Alert(Alert.AlertType.ERROR,"Payment not updated successfully").show();
            }
        }catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

}
