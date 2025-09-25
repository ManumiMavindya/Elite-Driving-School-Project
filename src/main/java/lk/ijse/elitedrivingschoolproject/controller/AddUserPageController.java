package lk.ijse.elitedrivingschoolproject.controller;

import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import lk.ijse.elitedrivingschoolproject.bo.BOFactory;
import lk.ijse.elitedrivingschoolproject.bo.BOTypes;
import lk.ijse.elitedrivingschoolproject.bo.custom.UserBO;
import lk.ijse.elitedrivingschoolproject.dto.UserDTO;
import lk.ijse.elitedrivingschoolproject.dto.tm.UserTM;

import java.net.URL;
import java.util.ResourceBundle;

public class AddUserPageController implements Initializable {

    @FXML
    private Button addUserbtn;

    @FXML
    private TextField userAgetxt;

    @FXML
    private TextField userContacttxt;

    @FXML
    private TextField userEmailtxt;

    @FXML
    private Label userIdlbl;

    @FXML
    private TextField userNametxt;

    @FXML
    private TextField userPasswordtxt;

    @FXML
    private ComboBox<UserTM> rolecmb;

    private final UserBO userBO = (UserBO) BOFactory.getInstance().getBO(BOTypes.USER);

    private final String passwordRegex = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=]).{8,}$";
    private final String contactNumberRegex = "^[0-9]{10}$";
    private final String emailRegex = "^[\\w.-]+@[\\w.-]+\\.[a-zA-Z]{2,6}$";

    String ageInput = userAgetxt.getText();
    boolean isValidAge = ageInput.matches("^(1[89]|[2-3][0-9]|4[0-5])$");

    @FXML
    void addUserbtnOnAction(ActionEvent event) {

        try {
            String userId = userIdlbl.getText();
            String userName = userNametxt.getText();
            int age = Integer.parseInt(userAgetxt.getText());
            String email = userEmailtxt.getText();
            String password = userPasswordtxt.getText();
            String contactNumber = userContacttxt.getText();
            String role = rolecmb.getSelectionModel().getSelectedItem().toString();

            if (!password.matches(passwordRegex)) {
                addUserbtn.setDisable(true);
                new Alert(Alert.AlertType.ERROR, "Password is incorrect").show();
                return;
            }
            if (!contactNumber.matches(contactNumberRegex)) {
                addUserbtn.setDisable(true);
                new Alert(Alert.AlertType.ERROR, "Contact number is incorrect").show();
                return;
            }
            if (!email.matches(emailRegex)) {
                addUserbtn.setDisable(true);
                new Alert(Alert.AlertType.ERROR, "Email is incorrect").show();
                return;
            }

            if (!isValidAge) {
                addUserbtn.setDisable(true);
                new Alert(Alert.AlertType.ERROR, "Age is incorrect").show();
                return;
            }

            //String encryptedPassword = PasswordUtils.hashPassword(password);

            boolean isSaved = userBO.saveUser(new UserDTO(
                    userId,
                    userName,
                    age,
                    email,
                    password,
                    contactNumber,
                    role
            ));
            if (isSaved) {
                new Alert(Alert.AlertType.INFORMATION, "User added successfully").show();
            }else {
                new Alert(Alert.AlertType.ERROR, "User could not be added").show();
            }
        } catch (Exception e) {
            new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        try {
            userIdlbl.setText(userBO.generateNewUserID());
            //rolecmb.setItems(FXCollections.observableArrayList("Admin","User"));
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

    public void loadData(UserDTO userDTO) {

        userIdlbl.setText(userDTO.getUserId());
        userNametxt.setText(userDTO.getUsername());
        userAgetxt.setText(String.valueOf(userDTO.getAge()));
        userEmailtxt.setText(userDTO.getEmail());
        userPasswordtxt.setText(userDTO.getPassword());
        userContacttxt.setText(userDTO.getContactNumber());
    }

    //update button
}

