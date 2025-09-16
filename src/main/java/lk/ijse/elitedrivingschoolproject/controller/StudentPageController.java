package lk.ijse.elitedrivingschoolproject.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;

public class StudentPageController {

    @FXML
    private Button addStudentbtn;

    @FXML
    private TableColumn<?, ?> colStudentAction;

    @FXML
    private TableColumn<?, ?> colStudentAddress;

    @FXML
    private TableColumn<?, ?> colStudentBirth;

    @FXML
    private TableColumn<?, ?> colStudentCourses;

    @FXML
    private TableColumn<?, ?> colStudentEmailAddress;

    @FXML
    private TableColumn<?, ?> colStudentFullName;

    @FXML
    private TableColumn<?, ?> colStudentGender;

    @FXML
    private TableColumn<?, ?> colStudentId;

    @FXML
    private TableColumn<?, ?> colStudentNationalID;

    @FXML
    private TableColumn<?, ?> colStudentPhoneNumber;

    @FXML
    private TextField seachStudenttxt;

    @FXML
    private TableView<?> studenttbl;

    @FXML
    void addStudentbtnOnAction(ActionEvent event) {

    }

}
