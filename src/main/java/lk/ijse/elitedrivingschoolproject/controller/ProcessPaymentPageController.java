package lk.ijse.elitedrivingschoolproject.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

public class ProcessPaymentPageController {

    @FXML
    private TextField amounttxt;

    @FXML
    private Button completeProcessPaymentbtn;

    @FXML
    private ComboBox<?> coursecmb;

    @FXML
    private ComboBox<?> paymentMethodcmb;

    @FXML
    private ComboBox<?> studentcmb;

    @FXML
    private Label transactionIdlbl;

    @FXML
    void completeProcessPaymentbtnOnAction(ActionEvent event) {

    }

}
