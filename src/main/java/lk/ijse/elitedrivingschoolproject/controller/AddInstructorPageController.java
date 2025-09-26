package lk.ijse.elitedrivingschoolproject.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import lk.ijse.elitedrivingschoolproject.bo.BOFactory;
import lk.ijse.elitedrivingschoolproject.bo.BOTypes;
import lk.ijse.elitedrivingschoolproject.bo.custom.InstructorBO;
import lk.ijse.elitedrivingschoolproject.dto.InstructorDTO;

import java.net.URL;
import java.time.LocalDate;
import java.util.ResourceBundle;

public class AddInstructorPageController implements Initializable {

    @FXML
    private DatePicker InstructorBirthtxt;

    @FXML
    private Button instructorAddFormbtn;

    @FXML
    private TextField instructorAddresstxt;

    @FXML
    private ComboBox<String> instructorCoursecombo;

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
    private ComboBox<String> instructorStatusCombo;

    @FXML
    private Button updateInstructorbtn;


    InstructorBO instructorBO = (InstructorBO) BOFactory.getInstance().getBO(BOTypes.INSTRUCTOR) ;

    private final String emailRegex = "[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}\n";
    private final String phoneRegex = "(\\+?\\d{1,3}[- ]?)?\\d{10}\n";
    private final String licenceNumberRegex = "[A-Z0-9]{6,12}\n";

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        try {
            instructorIdlbl.setText(instructorBO.generateNewInstructorID());
        }catch (Exception e){
            throw new RuntimeException(e);
        }
    }

    @FXML
    void instructorAddFormbtnOnAction(ActionEvent event) {

        String fullName = instructorFullNametxt.getText();
        LocalDate dob =  InstructorBirthtxt.getValue();
        String phone  = instructorPhoneNumbertxt.getText();
        String address = instructorAddresstxt.getText();
        String email = instructorEmailAddresstxt.getText();
        String licence  = instructorLicenceNumbertxt.getText();
        String courseId = instructorStatusCombo.getValue();
        String status = instructorStatusCombo.getValue();

        boolean isValidEmail = email.matches(emailRegex);
        boolean isValidPhone = phone.matches(phoneRegex);
        boolean isValidLicenceNumber = address.matches(licenceNumberRegex);

        instructorEmailAddresstxt.setStyle(instructorEmailAddresstxt.getStyle() +";-fx-border-color: red");
        instructorPhoneNumbertxt.setStyle(instructorPhoneNumbertxt.getStyle() +";-fx-border-color: red");
        instructorLicenceNumbertxt.setStyle(instructorLicenceNumbertxt.getStyle() +";-fx-border-color: red");

        if (fullName.isEmpty() || dob == null || phone.isEmpty() || address.isEmpty() || email.isEmpty() || licence.isEmpty() || courseId.isEmpty() || status.isEmpty()) {
            instructorAddFormbtn.setDisable(true);
            new Alert(Alert.AlertType.ERROR, "Please fill all the fields", ButtonType.OK).show();
            return;
        }
         if (!isValidEmail) {
             instructorEmailAddresstxt.setStyle(instructorEmailAddresstxt.getStyle() +";-fx-border-color: red");
             new Alert(Alert.AlertType.ERROR, "Invalid email format ", ButtonType.OK).show();
             return;
         }
         if (!isValidPhone) {
             instructorPhoneNumbertxt.setStyle(instructorPhoneNumbertxt.getStyle() +";-fx-border-color: red");
             new Alert(Alert.AlertType.ERROR, "Invalid phone number format ", ButtonType.OK).show();
             return;
         }
         if (!isValidLicenceNumber) {
             instructorLicenceNumbertxt.setStyle(instructorLicenceNumbertxt.getStyle() +";-fx-border-color: red");
             new Alert(Alert.AlertType.ERROR, "Invalid licence number format", ButtonType.OK).show();
             return;
         }
         try {
             boolean isSaved = instructorBO.saveInstructor(InstructorDTO.builder()
                     .instructorId(instructorIdlbl.getText())
                     .instructorFullName(fullName)
                     .instructorBirthDate(dob)
                     .instructorPhone(phone)
                     .instructorAddress(address)
                     .instructorEmail(email)
                     .instructorLicenceNumber(licence)
                     .courseId(courseId)
                     .status(status)
                     .build());

             if (isSaved) {
                 new Alert(Alert.AlertType.INFORMATION, "Instructor saved successfully!!", ButtonType.OK).show();
             }else {
                 new Alert(Alert.AlertType.ERROR, "Instructor could not be saved!!", ButtonType.OK).show();
             }
         }catch (Exception e){
             throw new RuntimeException(e);
         }
    }

    public void loadData(InstructorDTO instructorDTO) throws Exception {

        instructorIdlbl.setText(instructorDTO.getInstructorId());
        instructorFullNametxt.setText(instructorDTO.getInstructorFullName());
        InstructorBirthtxt.setValue(instructorDTO.getInstructorBirthDate());
        instructorPhoneNumbertxt.setText(instructorDTO.getInstructorPhone());
        instructorAddresstxt.setText(instructorDTO.getInstructorAddress());
        instructorEmailAddresstxt.setText(instructorDTO.getInstructorEmail());
        instructorLicenceNumbertxt.setText(instructorDTO.getInstructorLicenceNumber());
        instructorCoursecombo.setValue(instructorDTO.getCourseId());
        instructorStatusCombo.setValue(instructorDTO.getStatus());
    }

    public void updateInstructorbtnOnAction(ActionEvent actionEvent) {

        String fullName = instructorFullNametxt.getText();
        LocalDate dob =  InstructorBirthtxt.getValue();
        String phone  = instructorPhoneNumbertxt.getText();
        String address = instructorAddresstxt.getText();
        String email = instructorEmailAddresstxt.getText();
        String licence  = instructorLicenceNumbertxt.getText();
        String courseId = instructorStatusCombo.getValue();
        String status = instructorStatusCombo.getValue();

        boolean isValidEmail = email.matches(emailRegex);
        boolean isValidPhone = phone.matches(phoneRegex);
        boolean isValidLicenceNumber = address.matches(licenceNumberRegex);

        instructorEmailAddresstxt.setStyle(instructorEmailAddresstxt.getStyle() +";-fx-border-color: red");
        instructorPhoneNumbertxt.setStyle(instructorPhoneNumbertxt.getStyle() +";-fx-border-color: red");
        instructorLicenceNumbertxt.setStyle(instructorLicenceNumbertxt.getStyle() +";-fx-border-color: red");

        if (fullName.isEmpty() || dob == null || phone.isEmpty() || address.isEmpty() || email.isEmpty() || licence.isEmpty() || courseId.isEmpty() || status.isEmpty()) {
            updateInstructorbtn.setDisable(true);
            new Alert(Alert.AlertType.ERROR, "Please fill all the fields", ButtonType.OK).show();
            return;
        }
        if (!isValidEmail) {
            instructorEmailAddresstxt.setStyle(instructorEmailAddresstxt.getStyle() +";-fx-border-color: red");
            new Alert(Alert.AlertType.ERROR, "Invalid email format ", ButtonType.OK).show();
            return;
        }
        if (!isValidPhone) {
            instructorPhoneNumbertxt.setStyle(instructorPhoneNumbertxt.getStyle() +";-fx-border-color: red");
            new Alert(Alert.AlertType.ERROR, "Invalid phone number format ", ButtonType.OK).show();
            return;
        }
        if (!isValidLicenceNumber) {
            instructorLicenceNumbertxt.setStyle(instructorLicenceNumbertxt.getStyle() +";-fx-border-color: red");
            new Alert(Alert.AlertType.ERROR, "Invalid licence number format", ButtonType.OK).show();
            return;
        }
        try {
            boolean isUpdated = instructorBO.updateInstructor(InstructorDTO.builder()
                    .instructorId(instructorIdlbl.getText())
                    .instructorFullName(fullName)
                    .instructorBirthDate(dob)
                    .instructorPhone(phone)
                    .instructorAddress(address)
                    .instructorEmail(email)
                    .instructorLicenceNumber(licence)
                    .courseId(courseId)
                    .status(status)
                    .build());

            if (isUpdated) {
                new Alert(Alert.AlertType.INFORMATION, "Instructor updated successfully!!", ButtonType.OK).show();
            }else {
                new Alert(Alert.AlertType.ERROR, "Instructor could not be update!!", ButtonType.OK).show();
            }
        }catch (Exception e){
            throw new RuntimeException(e);
        }
    }


}
