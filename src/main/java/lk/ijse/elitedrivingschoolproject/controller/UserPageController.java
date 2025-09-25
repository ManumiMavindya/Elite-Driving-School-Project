package lk.ijse.elitedrivingschoolproject.controller;

import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.Pane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import lk.ijse.elitedrivingschoolproject.bo.BOFactory;
import lk.ijse.elitedrivingschoolproject.bo.BOTypes;
import lk.ijse.elitedrivingschoolproject.bo.custom.UserBO;
import lk.ijse.elitedrivingschoolproject.dto.UserDTO;
import lk.ijse.elitedrivingschoolproject.dto.tm.UserTM;

import java.io.IOException;
import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;

public class UserPageController implements Initializable {

    @FXML
    private Button addUserbtn;

    @FXML
    private TableColumn<?, ?> colUserAction;

    @FXML
    private TableColumn<UserTM, Integer> colUserAge;

    @FXML
    private TableColumn<UserTM, String> colUserContactNumber;

    @FXML
    private TableColumn<UserTM, String> colUserEmail;

    @FXML
    private TableColumn<UserTM, String> colUserId;

    @FXML
    private TableColumn<UserTM, String> colUserName;

    @FXML
    private TableColumn<UserTM, String> colUserPassword;

    @FXML
    private TableColumn<UserTM, String> colRole;

    @FXML
    private TextField searchUsertxt;

    @FXML
    private TableView<UserTM> usertbl;

    private final UserBO userBO = (UserBO) BOFactory.getInstance().getBO(BOTypes.USER);


    @FXML
    void addUserbtnOnAction(ActionEvent event) {

        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/views/addUserForm.fxml"));
            Parent parent = fxmlLoader.load();

            Stage stage = new Stage();
            stage.setTitle("Add User");
            stage.setScene(new Scene(parent));
            stage.showAndWait();

            loadAllUsers();

        } catch (IOException e) {
            Alert alert = new Alert(Alert.AlertType.ERROR, e.getMessage());
            alert.showAndWait();
            e.printStackTrace();
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        colUserId.setCellValueFactory(new PropertyValueFactory<>("userId"));
        colUserName.setCellValueFactory(new PropertyValueFactory<>("username"));
        colUserAge.setCellValueFactory(new PropertyValueFactory<>("age"));
        colUserEmail.setCellValueFactory(new PropertyValueFactory<>("email"));
        colUserPassword.setCellValueFactory(new PropertyValueFactory<>("password"));
        colUserContactNumber.setCellValueFactory(new PropertyValueFactory<>("contactNumber"));
        colRole.setCellValueFactory(new PropertyValueFactory<>("role"));
        colUserAction.setCellValueFactory(new PropertyValueFactory<>("action"));

        try {
            loadAllUsers();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    private void loadAllUsers() {

        try {
            usertbl.setItems(FXCollections.observableArrayList(
                    userBO.getAllUsers().stream().map(userDTO -> {

                        Pane action = new Pane();

                        Button btnEdit = new Button("✏");
                        btnEdit.setStyle("-fx-background-color: #4CAF50; -fx-text-fill: white; -fx-font-weight: bold;");
                        btnEdit.setPrefWidth(30);
                        btnEdit.setLayoutX(40);
                        btnEdit.setCursor(javafx.scene.Cursor.HAND);
                        btnEdit.setOnAction(event -> onUpdate(userDTO));

                        Button btnDelete = new Button("🗑");
                        btnDelete.setStyle("-fx-background-color: #f44336; -fx-text-fill: white; -fx-font-weight: bold;");
                        btnDelete.setPrefWidth(30);
                        btnDelete.setLayoutX(0);
                        btnDelete.setCursor(javafx.scene.Cursor.HAND);
                        btnDelete.setOnAction(event -> onDelete(userDTO.getUserId()));

                        action.getChildren().addAll(btnDelete, btnEdit);

                        return new UserTM(
                                userDTO.getUserId(),
                                userDTO.getUsername(),
                                userDTO.getAge(),
                                userDTO.getEmail(),
                                userDTO.getPassword(),
                                userDTO.getContactNumber(),
                                userDTO.getRole(),
                                action
                        );
                    }).toList()
            ));
        }catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public void onDelete(String id){

        Alert alert = new Alert(
                Alert.AlertType.CONFIRMATION,
                "Are you sure?",
                ButtonType.YES,
                ButtonType.NO
        );
        alert.setTitle("Delete User");

        Optional<ButtonType> result = alert.showAndWait();

        if (result.isPresent() && result.get() == ButtonType.YES) {

            try {
                boolean isDeleted = userBO.deleteUser(id);
                if (isDeleted) {
                    new Alert(Alert.AlertType.INFORMATION, "User deleted successfully!").show();
                    loadAllUsers();
                } else {
                    new Alert(Alert.AlertType.ERROR, "Failed!!").show();
                }
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }
    }

    public void onUpdate(UserDTO userDTO){

        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/views/addUserForm.fxml"));
            Parent parent = fxmlLoader.load();

            AddUserPageController controller = fxmlLoader.getController();
            controller.loadData(userDTO);

            Stage stage = new Stage();
            stage.setTitle("Update User");
            stage.setScene(new Scene(parent));
            stage.showAndWait();

            loadAllUsers();

        } catch (IOException e) {
            new Alert(Alert.AlertType.ERROR, "Failed to open!").show();
        }
    }
}
