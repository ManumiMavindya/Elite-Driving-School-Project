package lk.ijse.elitedrivingschoolproject.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import lk.ijse.elitedrivingschoolproject.util.AuthUtil;

import java.net.URL;
import java.util.ResourceBundle;

public class DashboardController implements Initializable {

    @FXML
    private Button coursesbtn;

    @FXML
    private Button dashboardbtn;

    @FXML
    private Button instructorsbtn;

    @FXML
    private Button lessonsbtn;

    @FXML
    private Button logOutbtn;

    @FXML
    private AnchorPane adminMainAnchorPane;

    @FXML
    private Button paymentbtn;

    @FXML
    private AnchorPane adminSecondAnchorPane;

    @FXML
    private Button studentbtn;

    @FXML
    private Button userbtn;

    @FXML
    void coursesbtnOnAction(ActionEvent event) {

        navigateTo("/views/coursesPage.fxml");


    }

    @FXML
    void dashboardbtnOnAction(ActionEvent event) {

        navigateTo("/views/mainDashboard.fxml");
    }

    @FXML
    void instructorsbtnOnAction(ActionEvent event) {

        navigateTo("/views/instructorPage.fxml");
    }

    @FXML
    void lessonsbtnOnAction(ActionEvent event) {

        navigateTo("/views/lessonsPage.fxml");
    }

    @FXML
    void logOutbtnOnAction(ActionEvent event) {

        navigateTo("/views/logOutPage.fxml");
    }

    @FXML
    void paymentbtnOnAction(ActionEvent event) {

        navigateTo("/views/paymentPage.fxml");
    }

    @FXML
    void studentbtnOnAction(ActionEvent event) {

        navigateTo("/views/studentPage.fxml");
    }

    @FXML
    void userbtnOnAction(ActionEvent event) {

        navigateTo("/views/userPage.fxml");
    }


    void navigateTo(String path) {
        try {

            adminMainAnchorPane.getChildren().clear();

            AnchorPane anchorPane = FXMLLoader.load(getClass().getResource(path));

            anchorPane.prefWidthProperty().bind(adminMainAnchorPane.widthProperty());
            anchorPane.prefHeightProperty().bind(adminMainAnchorPane.heightProperty());

            adminMainAnchorPane.getChildren().add(anchorPane);

            if (path.equals("/views/AdminDashboard.fxml")) {
                dashboardbtn.setDisable(true);
                instructorsbtn.setDisable(true);
                lessonsbtn.setDisable(true);
                studentbtn.setDisable(true);
                paymentbtn.setDisable(true);
                logOutbtn.setDisable(true);
                coursesbtn.setDisable(true);
            }
        } catch (Exception e) {
            new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
            e.printStackTrace();
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        navigateTo("/views/mainDashboard.fxml");
        restrictions();

    }

    public void restrictions() {

//        if (AuthUtil.getRole().equalsIgnoreCase(String.valueOf(Role.USER))) {
//            userbtn.setVisible(false);
//            coursesbtn.setVisible(false);
//            instructorsbtn.setVisible(false);
//
//        }
//    }

    }
}

