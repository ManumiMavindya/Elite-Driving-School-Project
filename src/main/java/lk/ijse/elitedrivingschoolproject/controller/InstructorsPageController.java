package lk.ijse.elitedrivingschoolproject.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import lk.ijse.elitedrivingschoolproject.bo.BOFactory;
import lk.ijse.elitedrivingschoolproject.bo.BOTypes;
import lk.ijse.elitedrivingschoolproject.bo.custom.InstructorBO;

import java.net.URL;
import java.util.ResourceBundle;

public class InstructorsPageController implements Initializable {

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

    private final InstructorBO instructorBO = (InstructorBO) BOFactory.getInstance().getBO(BOTypes.INSTRUCTOR);

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        colInstructorId.setCellValueFactory(new PropertyValueFactory<>(""));
    }

    @FXML
    void addInstructorbtnOnAction(ActionEvent event) {

    }

}

