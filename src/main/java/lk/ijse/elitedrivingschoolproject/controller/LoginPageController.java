package lk.ijse.elitedrivingschoolproject.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import lk.ijse.elitedrivingschoolproject.bo.BOFactory;
import lk.ijse.elitedrivingschoolproject.bo.BOTypes;
import lk.ijse.elitedrivingschoolproject.bo.custom.UserBO;
import lk.ijse.elitedrivingschoolproject.config.FactoryConfiguration;
import lk.ijse.elitedrivingschoolproject.dto.UserDTO;
import lk.ijse.elitedrivingschoolproject.util.PasswordUtils;
import org.hibernate.Session;
import lk.ijse.elitedrivingschoolproject.util.AuthUtil;

import java.net.URL;
import java.util.ResourceBundle;

public class LoginPageController implements Initializable {

    @FXML
    private Button forgotPasswordbtn;

    @FXML
    private Button loginbtn;

    @FXML
    private TextField passwordtxt;

    @FXML
    private TextField userNametxt;

    private final String userNamePattern = "^[A-Za-z0-9_ ]{3,}$";
    private final String passwordPattern = "^[A-Za-z0-9@#$%^&+=]{6,}$";

    UserBO userBO = (UserBO) BOFactory.getInstance().getBO(BOTypes.USER);


    @FXML
    void forgotPasswordbtnOnAction(ActionEvent event) {

    }

    @FXML
    void loginbtnOnAction(ActionEvent event) {

        String inputUsername = userNametxt.getText();
        String inputPassword = passwordtxt.getText();

        if (inputUsername.isEmpty() || inputPassword.isEmpty()) {
            new Alert(Alert.AlertType.ERROR, "Please fill all the fields", ButtonType.OK).show();
            return;
        }

        try (Session session = FactoryConfiguration.getInstance().getSession()) {
            UserDTO user = userBO.getUserByEmail(inputUsername);

            if (user != null && PasswordUtils.checkPassword(inputPassword, user.getPassword())) {

                AuthUtil.setCurrentUser(user);

                Parent dashboardRoot = FXMLLoader.load(getClass().getResource("/views/Dashboard.fxml"));
                Stage dashboardStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                dashboardStage.setScene(new Scene(dashboardRoot));
                dashboardStage.show();
            } else {
                new Alert(Alert.AlertType.ERROR, "Invalid Username or Password", ButtonType.OK).show();
            }
        } catch (Exception e) {
            e.printStackTrace();
            new Alert(Alert.AlertType.ERROR, "Login Fail", ButtonType.OK).show();
        }

    }

    private void validFields() {

        boolean isValidUsername = userNametxt.getText().matches(userNamePattern);
        boolean isValidPassword = passwordtxt.getText().matches(passwordPattern);

        userNametxt.setStyle("-fx-border-color: #7367F0; -fx-border-radius: 12px; -fx-background-radius: 12px;");
        passwordtxt.setStyle("-fx-border-color: #7367F0; -fx-border-radius: 12px; -fx-background-radius: 12px;");

        if (!isValidUsername)
            userNametxt.setStyle("-fx-border-color: red; -fx-border-radius: 12px; -fx-background-radius: 12px;");
        if (!isValidPassword)
            passwordtxt.setStyle("-fx-border-color: red; -fx-border-radius: 12px; -fx-background-radius: 12px;");

        loginbtn.setDisable(!(isValidUsername && isValidPassword));

    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        userNametxt.textProperty().addListener((observable, oldValue, newValue) -> validFields());
        passwordtxt.textProperty().addListener((observable, oldValue, newValue) -> validFields());
        loginbtn.setDisable(true);
    }
}
