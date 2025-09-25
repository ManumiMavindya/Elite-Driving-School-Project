package lk.ijse.elitedrivingschoolproject.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import lk.ijse.elitedrivingschoolproject.dto.CourseDTO;
import lk.ijse.elitedrivingschoolproject.dto.StudentDTO;

import java.net.URL;
import java.util.ResourceBundle;

public class AddStudentPageController implements Initializable {

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
    private ComboBox<String> studentGendercombo;

    @FXML
    private Label studentIdlbl;

    @FXML
    private TextField studentNationalIDtxt;

    @FXML
    private TextField studentPhoneNumbertxt;

    @FXML
    void studentAddFormbtnOnAction(ActionEvent event) {

    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }

    public void loadData(StudentDTO studentDTO) {}


    //update button

}
