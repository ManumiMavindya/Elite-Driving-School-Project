package lk.ijse.elitedrivingschoolproject.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import lk.ijse.elitedrivingschoolproject.dto.InstructorDTO;
import lk.ijse.elitedrivingschoolproject.dto.PaymentDTO;

import java.net.URL;
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
    void completeProcessPaymentbtnOnAction(ActionEvent event) {

    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }

    public void loadData(PaymentDTO paymentDTO) throws Exception {

        transactionIdlbl.setText(paymentDTO.getTransactionId());
        studentcmb.setValue(paymentDTO.getStudentId());
        coursecmb.setValue(paymentDTO.getCourseId());
        datepicker.setValue(paymentDTO.getPaymentDate());
       // amounttxt.setText(paymentDTO.getPaymentAmount());




    }

    // update button
}
