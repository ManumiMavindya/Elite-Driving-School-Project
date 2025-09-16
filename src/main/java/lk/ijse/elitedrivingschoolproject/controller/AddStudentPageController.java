package lk.ijse.elitedrivingschoolproject.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

public class AddStudentPageController {

    @FXML
    private Button studentAddFormbtn;

    @FXML
    private TextField studentAddresstxt;

    @FXML
    private DatePicker studentBirthpicker;

    @FXML
    private CheckBox studentCourse1box;

    @FXML
    private CheckBox studentCourse2box;

    @FXML
    private CheckBox studentCourse3box;

    @FXML
    private CheckBox studentCourse4box;

    @FXML
    private CheckBox studentCourse5box;

    @FXML
    private TextField studentEmailAddresstxt;

    @FXML
    private TextField studentFullNametxt;

    @FXML
    private ComboBox<?> studentGendercombo;

    @FXML
    private Label studentIdlbl;

    @FXML
    private TextField studentNationalIDtxt;

    @FXML
    private TextField studentPhoneNumbertxt;

    @FXML
    void studentAddFormbtnOnAction(ActionEvent event) {

    }

}
