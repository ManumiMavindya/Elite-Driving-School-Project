package lk.ijse.elitedrivingschoolproject.controller;

import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Cursor;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import lk.ijse.elitedrivingschoolproject.bo.BOFactory;
import lk.ijse.elitedrivingschoolproject.bo.BOTypes;
import lk.ijse.elitedrivingschoolproject.bo.custom.StudentBO;
import lk.ijse.elitedrivingschoolproject.dto.CourseDTO;
import lk.ijse.elitedrivingschoolproject.dto.StudentDTO;
import lk.ijse.elitedrivingschoolproject.dto.tm.StudentTM;

import java.io.IOException;
import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;

public class StudentPageController implements Initializable {

    @FXML
    private Button addStudentbtn;

    @FXML
    private TableColumn<?, ?> colStudentAction;

    @FXML
    private TableColumn<StudentTM, String> colStudentAddress;

    @FXML
    private TableColumn<StudentTM, String> colStudentBirth;

    @FXML
    private TableColumn<StudentTM, String> colStudentCourses;

    @FXML
    private TableColumn<StudentTM, String> colStudentEmailAddress;

    @FXML
    private TableColumn<StudentTM, String> colStudentFullName;

    @FXML
    private TableColumn<StudentTM, String> colStudentGender;

    @FXML
    private TableColumn<StudentTM, String> colStudentId;

    @FXML
    private TableColumn<StudentTM, String> colStudentNationalID;

    @FXML
    private TableColumn<StudentTM, String> colStudentPhoneNumber;

    @FXML
    private TextField seachStudenttxt;

    @FXML
    private TableView<StudentTM> studenttbl;

    private final StudentBO studentsBO = (StudentBO) BOFactory.getInstance().getBO(BOTypes.STUDENT);

    @FXML
    void addStudentbtnOnAction(ActionEvent event) {

        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/views/addStudentForm.fxml"));
            Parent parent = fxmlLoader.load();

            Stage stage = new Stage();
            stage.setTitle("Add Student");
            stage.setScene(new Scene(parent));
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.setResizable(false);
            stage.showAndWait();

            loadAllStudents();

        } catch (IOException e) {
            Alert alert = new Alert(Alert.AlertType.ERROR,e.getMessage());
            alert.showAndWait();
            e.printStackTrace();
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        colStudentId.setCellValueFactory(new PropertyValueFactory<>("studentId"));
        colStudentFullName.setCellValueFactory(new PropertyValueFactory<>("studentFullName"));
        colStudentBirth.setCellValueFactory(new PropertyValueFactory<>("studentBirthDate"));
        colStudentGender.setCellValueFactory(new PropertyValueFactory<>("studentGender"));
        colStudentPhoneNumber.setCellValueFactory(new PropertyValueFactory<>("studentPhone"));
        colStudentAddress.setCellValueFactory(new PropertyValueFactory<>("studentAddress"));
        colStudentEmailAddress.setCellValueFactory(new PropertyValueFactory<>("studentEmail"));
        colStudentNationalID.setCellValueFactory(new PropertyValueFactory<>("studentNationalId"));
        colStudentCourses.setCellValueFactory(new PropertyValueFactory<>("studentCourseId"));
        colStudentAction.setCellValueFactory(new PropertyValueFactory<>("action"));

        try {
            loadAllStudents();
        }catch (Exception e){
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

    private void loadAllStudents() {

        try {
            studenttbl.setItems(FXCollections.observableArrayList(
                    studentsBO.getAllStudents().stream().map(studentDTO -> {

                        Pane action = new Pane();

                        Button btnEdit = new Button("✏");
                        btnEdit.setStyle("-fx-background-color: #4CAF50; -fx-text-fill: white; -fx-font-weight: bold;");
                        btnEdit.setPrefWidth(30);
                        btnEdit.setLayoutX(40);
                        btnEdit.setCursor(Cursor.HAND);
                        btnEdit.setOnAction(event -> onUpdate(studentDTO));

                        Button btnDelete = new Button("🗑");
                        btnDelete.setStyle("-fx-background-color: #f44336; -fx-text-fill: white; -fx-font-weight: bold;");
                        btnDelete.setPrefWidth(30);
                        btnDelete.setLayoutX(0);
                        btnDelete.setCursor(Cursor.HAND);
                        btnDelete.setOnAction(event -> onDelete(studentDTO.getStudentId()));

                        action.getChildren().addAll(btnDelete, btnEdit);

                        System.out.println(studentDTO.getCourses());

                        String selectedCourseNamesString =  studentDTO.getCourses() == null ? "" :
                                String.join(", ", studentDTO.getCourses().stream()
                                        .map(CourseDTO::getCourseName)
                                        .toList());


                        return new StudentTM(
                                studentDTO.getStudentId(),
                                studentDTO.getStudentFullName(),
                                studentDTO.getStudentBirthDate(),
                                studentDTO.getStudentGender(),
                                studentDTO.getStudentPhone(),
                                studentDTO.getStudentAddress(),
                                studentDTO.getStudentEmail(),
                                studentDTO.getNationalId(),
                                selectedCourseNamesString,
                                action
                        );
                    }).toList()
            ));
        }catch (Exception e){
            throw new RuntimeException(e);
        }
    }

    public void onDelete(String id){

        Alert alert = new Alert(Alert.AlertType.CONFIRMATION,
                "Are you sure?",
                ButtonType.YES,
                ButtonType.NO);

        alert.setTitle("Delete Student");

        Optional<ButtonType> result = alert.showAndWait();

        if (result.isPresent() && result.get() == ButtonType.YES) {

            try {
                boolean isDeleted = studentsBO.deleteStudent(id);
                if (isDeleted) {
                    new Alert(Alert.AlertType.INFORMATION,"Student Deleted successfully!!").show();
                    loadAllStudents();
                }else {
                    new Alert(Alert.AlertType.ERROR,"Student Not Deleted!!").show();
                }
            }catch (Exception e) {
                throw new RuntimeException(e);
            }
        }
    }

    public void onUpdate(StudentDTO studentDTO) {

        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/views/addStudentForm.fxml"));
            Parent parent = loader.load();

            AddStudentPageController controller = loader.getController();
            controller.loadData(studentDTO);

            Stage stage = new Stage();
            stage.setTitle("Update Student");
            stage.setScene(new Scene(parent));
            stage.showAndWait();

            loadAllStudents();

        } catch (IOException e) {
            new Alert(Alert.AlertType.ERROR, e.getMessage(), ButtonType.OK).show();
        }
    }

    @FXML
    void onClickTable(MouseEvent event) {

    }

}
