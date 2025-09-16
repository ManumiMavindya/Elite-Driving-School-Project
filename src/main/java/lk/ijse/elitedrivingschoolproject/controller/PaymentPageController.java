package lk.ijse.elitedrivingschoolproject.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

public class PaymentPageController {

    @FXML
    private TableColumn<?, ?> colAction;

    @FXML
    private TableColumn<?, ?> colAmount;

    @FXML
    private TableColumn<?, ?> colCourse;

    @FXML
    private TableColumn<?, ?> colDate;

    @FXML
    private TableColumn<?, ?> colStatus;

    @FXML
    private TableColumn<?, ?> colStudentName;

    @FXML
    private TableColumn<?, ?> colTransactionId;

    @FXML
    private Label completePaymentsCountlbl;

    @FXML
    private TableView<?> paymenttbl;

    @FXML
    private Label pendingPaymentsCountlbl;

    @FXML
    private Button processPaymentbtn;

    @FXML
    void processPaymentbtnOnAction(ActionEvent event) {

    }

}
