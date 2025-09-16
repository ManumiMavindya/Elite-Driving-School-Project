package lk.ijse.elitedrivingschoolproject.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

public class AddInstructorPageController {

    @FXML
    private DatePicker InstructorBirthtxt;

    @FXML
    private Button instructorAddFormbtn;

    @FXML
    private TextField instructorAddresstxt;

    @FXML
    private ComboBox<?> instructorCoursecombo;

    @FXML
    private TextField instructorEmailAddresstxt;

    @FXML
    private TextField instructorFullNametxt;

    @FXML
    private Label instructorIdlbl;

    @FXML
    private TextField instructorLicenceNumbertxt;

    @FXML
    private TextField instructorPhoneNumbertxt;

    @FXML
    private ComboBox<?> instructorStatusCombo;

    @FXML
    void instructorAddFormbtnOnAction(ActionEvent event) {

    }

}
