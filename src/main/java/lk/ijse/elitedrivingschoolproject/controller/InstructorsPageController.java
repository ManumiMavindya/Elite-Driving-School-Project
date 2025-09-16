package lk.ijse.elitedrivingschoolproject.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;

public class InstructorsPageController {

    @FXML
    private TableView<?> Instructortbl;

    @FXML
    private Button addInstructorbtn;

    @FXML
    private TableColumn<?, ?> colInstructorAction;

    @FXML
    private TableColumn<?, ?> colInstructorAddresss;

    @FXML
    private TableColumn<?, ?> colInstructorCourse;

    @FXML
    private TableColumn<?, ?> colInstructorDateOfBirth;

    @FXML
    private TableColumn<?, ?> colInstructorEmailAddress;

    @FXML
    private TableColumn<?, ?> colInstructorFullName;

    @FXML
    private TableColumn<?, ?> colInstructorId;

    @FXML
    private TableColumn<?, ?> colInstructorLicenceNumber;

    @FXML
    private TableColumn<?, ?> colInstructorPhoneNumber;

    @FXML
    private TableColumn<?, ?> colInstructorStatus;

    @FXML
    private TextField searchInstructortxtf;

    @FXML
    void addInstructorbtnOnAction(ActionEvent event) {

    }

}

