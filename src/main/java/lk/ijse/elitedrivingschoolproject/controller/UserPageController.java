package lk.ijse.elitedrivingschoolproject.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;

public class UserPageController {

    @FXML
    private Button addUserbtn;

    @FXML
    private TableColumn<?, ?> colUserAction;

    @FXML
    private TableColumn<?, ?> colUserAge;

    @FXML
    private TableColumn<?, ?> colUserContactNumber;

    @FXML
    private TableColumn<?, ?> colUserEmail;

    @FXML
    private TableColumn<?, ?> colUserId;

    @FXML
    private TableColumn<?, ?> colUserName;

    @FXML
    private TableColumn<?, ?> colUserPassword;

    @FXML
    private TextField searchUsertxt;

    @FXML
    private TableView<?> usertbl;

    @FXML
    void addUserbtnOnAction(ActionEvent event) {

    }

}
