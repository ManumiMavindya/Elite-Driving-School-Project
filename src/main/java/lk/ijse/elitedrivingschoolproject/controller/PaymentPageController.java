package lk.ijse.elitedrivingschoolproject.controller;

import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import lk.ijse.elitedrivingschoolproject.bo.BOFactory;
import lk.ijse.elitedrivingschoolproject.bo.BOTypes;
import lk.ijse.elitedrivingschoolproject.bo.custom.PaymentBO;
import lk.ijse.elitedrivingschoolproject.bo.custom.UserBO;
import lk.ijse.elitedrivingschoolproject.dto.PaymentDTO;
import lk.ijse.elitedrivingschoolproject.dto.UserDTO;
import lk.ijse.elitedrivingschoolproject.dto.tm.PaymentTM;
import lk.ijse.elitedrivingschoolproject.dto.tm.UserTM;

import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.util.Optional;
import java.util.ResourceBundle;

public class PaymentPageController implements Initializable {

    @FXML
    private TableColumn<?, ?> colAction;

    @FXML
    private TableColumn<PaymentTM, Double> colAmount;

    @FXML
    private TableColumn<PaymentTM, String> colCourseId;

    @FXML
    private TableColumn<PaymentTM, LocalDate> colDate;

    @FXML
    private TableColumn<PaymentTM, String> colStatus;

    @FXML
    private TableColumn<PaymentTM, String> colStudentId;

    @FXML
    private TableColumn<PaymentTM, String> colTransactionId;


    @FXML
    private TableView<PaymentTM> paymenttbl;


    @FXML
    private Button processPaymentbtn;

    private final PaymentBO paymentBO = (PaymentBO) BOFactory.getInstance().getBO(BOTypes.PAYMENTS);

    @FXML
    void processPaymentbtnOnAction(ActionEvent event) {

        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/views/processPaymentForm.fxml"));
            Parent parent = fxmlLoader.load();

            Stage stage = new Stage();
            stage.setTitle("Add Payment");
            stage.setScene(new Scene(parent));
            stage.showAndWait();

            loadAllPayments();

        } catch (IOException e) {
            Alert alert = new Alert(Alert.AlertType.ERROR, e.getMessage());
            alert.showAndWait();
            e.printStackTrace();
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        colTransactionId.setCellValueFactory(new PropertyValueFactory<>("transactionId"));
        colStudentId.setCellValueFactory(new PropertyValueFactory<>("studentId"));
        colCourseId.setCellValueFactory(new PropertyValueFactory<>("courseId"));
        colDate.setCellValueFactory(new PropertyValueFactory<>("paymentDate"));
        colAmount.setCellValueFactory(new PropertyValueFactory<>("paymentAmount"));
        colStatus.setCellValueFactory(new PropertyValueFactory<>("paymentStatus"));
        colAction.setCellValueFactory(new PropertyValueFactory<>("action"));

        try {
            loadAllPayments();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    private void loadAllPayments() {

        try {
            paymenttbl.setItems(FXCollections.observableArrayList(
                    paymentBO.getAllPayments().stream().map(paymentDTO -> {

                        Pane action = new Pane();

                        Button btnEdit = new Button("✏");
                        btnEdit.setStyle("-fx-background-color: #4CAF50; -fx-text-fill: white; -fx-font-weight: bold;");
                        btnEdit.setPrefWidth(30);
                        btnEdit.setLayoutX(40);
                        btnEdit.setCursor(javafx.scene.Cursor.HAND);
                        btnEdit.setOnAction(event -> onUpdate(paymentDTO));

                        Button btnDelete = new Button("🗑");
                        btnDelete.setStyle("-fx-background-color: #f44336; -fx-text-fill: white; -fx-font-weight: bold;");
                        btnDelete.setPrefWidth(30);
                        btnDelete.setLayoutX(0);
                        btnDelete.setCursor(javafx.scene.Cursor.HAND);
                        btnDelete.setOnAction(event -> onDelete(paymentDTO.getTransactionId()));

                        action.getChildren().addAll(btnDelete, btnEdit);

                        return new PaymentTM(
                                paymentDTO.getTransactionId(),
                                paymentDTO.getStudentId(),
                                paymentDTO.getCourseId(),
                                paymentDTO.getPaymentDate(),
                                paymentDTO.getPaymentAmount(),
                                paymentDTO.getPaymentStatus(),
                                action
                        );
                    }).toList()
            ));
        }catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public void onDelete(String id){

        Alert alert = new Alert(
                Alert.AlertType.CONFIRMATION,
                "Are you sure?",
                ButtonType.YES,
                ButtonType.NO
        );
        alert.setTitle("Delete Payment");

        Optional<ButtonType> result = alert.showAndWait();

        if (result.isPresent() && result.get() == ButtonType.YES) {

            try {
                boolean isDeleted = paymentBO.deletePayment(id);
                if (isDeleted) {
                    new Alert(Alert.AlertType.INFORMATION, "Payment deleted successfully!").show();
                    loadAllPayments();
                } else {
                    new Alert(Alert.AlertType.ERROR, "Failed!!").show();
                }
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }
    }

    public void onUpdate(PaymentDTO paymentDTO){

        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/views/processPaymentForm.fxml"));
            Parent parent = fxmlLoader.load();

            ProcessPaymentPageController controller = fxmlLoader.getController();
            controller.loadData(paymentDTO);

            Stage stage = new Stage();
            stage.setTitle("Update Payment");
            stage.setScene(new Scene(parent));
            stage.showAndWait();

            loadAllPayments();

        } catch (IOException e) {
            new Alert(Alert.AlertType.ERROR, "Failed to open!").show();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
