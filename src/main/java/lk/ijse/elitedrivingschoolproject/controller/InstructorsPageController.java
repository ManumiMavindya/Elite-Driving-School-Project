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
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import lk.ijse.elitedrivingschoolproject.bo.BOFactory;
import lk.ijse.elitedrivingschoolproject.bo.BOTypes;
import lk.ijse.elitedrivingschoolproject.bo.custom.InstructorBO;
import lk.ijse.elitedrivingschoolproject.dto.InstructorDTO;
import lk.ijse.elitedrivingschoolproject.dto.tm.InstructorTM;

import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.util.Optional;
import java.util.ResourceBundle;

public class InstructorsPageController implements Initializable {

    @FXML
    private TableView<?> Instructortbl;

    @FXML
    private Button addInstructorbtn;

    @FXML
    private TableColumn<InstructorTM, Pane> colInstructorAction;

    @FXML
    private TableColumn<InstructorTM, String> colInstructorAddresss;

    @FXML
    private TableColumn<InstructorTM, String> colInstructorCourse;

    @FXML
    private TableColumn<InstructorTM, LocalDate> colInstructorDateOfBirth;

    @FXML
    private TableColumn<InstructorTM, String> colInstructorEmailAddress;

    @FXML
    private TableColumn<InstructorTM, String> colInstructorFullName;

    @FXML
    private TableColumn<InstructorTM, String> colInstructorId;

    @FXML
    private TableColumn<InstructorTM, String> colInstructorLicenceNumber;

    @FXML
    private TableColumn<InstructorTM, String> colInstructorPhoneNumber;

    @FXML
    private TableColumn<InstructorTM, String> colInstructorStatus;

    @FXML
    private TextField searchInstructortxtf;

    private final InstructorBO instructorBO = (InstructorBO) BOFactory.getInstance().getBO(BOTypes.INSTRUCTOR);

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        colInstructorId.setCellValueFactory(new PropertyValueFactory<>("instructorId"));
        colInstructorFullName.setCellValueFactory(new PropertyValueFactory<>("instructorFullName"));
        colInstructorDateOfBirth.setCellValueFactory(new PropertyValueFactory<>("instructorBirthDate"));
        colInstructorPhoneNumber.setCellValueFactory(new PropertyValueFactory<>("instructorPhone"));
        colInstructorAddresss.setCellValueFactory(new PropertyValueFactory<>("instructorAddress"));
        colInstructorEmailAddress.setCellValueFactory(new PropertyValueFactory<>("instructorEmail"));
        colInstructorLicenceNumber.setCellValueFactory(new PropertyValueFactory<>("instructorLicenceNumber"));
        colInstructorCourse.setCellValueFactory(new PropertyValueFactory<>("courseId"));
        colInstructorStatus.setCellValueFactory(new PropertyValueFactory<>("status"));
        colInstructorAction.setCellValueFactory(new PropertyValueFactory<>("action"));

        try {
            loadAllInstructors();
        }catch (Exception e){
            throw new RuntimeException(e);
        }

    }

    @FXML
    void addInstructorbtnOnAction(ActionEvent event) {

        try {
            FXMLLoader fxmlLoader= new FXMLLoader(getClass().getResource("/views/AddInstructor.fxml"));
            Parent parent = fxmlLoader.load();

            Stage stage = new Stage();
            stage.setTitle("Add Instructor");
            stage.setScene(new Scene(parent));
            stage.showAndWait();

            loadAllInstructors();

        }catch (Exception e){
            e.printStackTrace();
            new Alert(Alert.AlertType.ERROR, "Something went wrong, please try again").show();
        }

    }

    private void loadAllInstructors() throws Exception {

        try {
            Instructortbl.getItems(FXCollections.observableArrayList(
                    instructorBO.getAllInstructors().stream().map(instructorDTO -> {

                        Pane action = new Pane();

                        Button buttonUpdate = new Button("✒");
                        buttonUpdate.setStyle("-fx-background-color: #4CAF50; -fx-text-fill: white; -fx-font-weight: bold;");
                        buttonUpdate.setPrefWidth(30);
                        buttonUpdate.setLayoutX(40);
                        buttonUpdate.setCursor(javafx.scene.Cursor.HAND);
                        buttonUpdate.setOnAction(event -> updateOnAction(instructorDTO));

                        Button buttonDelete = new Button("🗑");
                        buttonDelete.setStyle("-fx-background-color: #f44336; -fx-text-fill: white; -fx-font-weight: bold;");
                        buttonDelete.setPrefWidth(30);
                        buttonDelete.setLayoutX(0);
                        buttonDelete.setCursor(javafx.scene.Cursor.HAND);
                        buttonDelete.setOnAction(event -> deleteOnAction(instructorDTO.getInstructorId()));

                        action.getChildren().addAll(buttonUpdate, buttonDelete);

                        return new InstructorTM(
                                instructorDTO.getInstructorId(),
                                instructorDTO.getInstructorFullName(),
                                instructorDTO.getInstructorBirthDate(),
                                instructorDTO.getInstructorPhone(),
                                instructorDTO.getInstructorAddress(),
                                instructorDTO.getInstructorEmail(),
                                instructorDTO.getInstructorLicenceNumber(),
                                instructorDTO.getCourseId(),
                                instructorDTO.getStatus(),
                                action
                        );
                    }).toList()
            ));
        }catch (Exception e){
            throw new RuntimeException(e);
        }
    }

    public void updateOnAction(InstructorDTO instructorDTO)  {

        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/views/InstructorsPage.fxml"));
            Parent parent = fxmlLoader.load();

            AddInstructorPageController controller = fxmlLoader.getController();
            controller.loadData(instructorDTO);

            Stage stage = new Stage();
            stage.setTitle("Update Instructor");
            stage.setScene(new Scene(parent));
            stage.showAndWait();

            loadAllInstructors();

        }catch (Exception e){
            new Alert(Alert.AlertType.ERROR, e.getMessage(), ButtonType.OK).show();
        }
    }

    public void deleteOnAction(String id) throws Exception {

        Alert alert = new Alert(Alert.AlertType.CONFIRMATION,
                "Are you sure?",
                ButtonType.YES,
                ButtonType.NO
        );
        alert.setTitle("Delete Instructor");
        Optional<ButtonType> result = alert.showAndWait();

        if (result.isPresent() && result.get() == ButtonType.YES) {

            try {
                boolean isDeleted = instructorBO.deleteInstructor(id);
                if (isDeleted) {
                    new Alert(Alert.AlertType.INFORMATION,"Instructor deleted Successfully!!").show();
                    loadAllInstructors();
                }else {
                    new Alert(Alert.AlertType.ERROR,"Instructor could not be deleted!!").show();
                }
            }catch (Exception e){
                throw new RuntimeException(e);
            }
        }
    }

    public void onClickedTable(MouseEvent mouseEvent) {
    }

}

