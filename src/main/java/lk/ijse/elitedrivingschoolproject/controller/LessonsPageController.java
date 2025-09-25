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
import javafx.stage.Stage;
import lk.ijse.elitedrivingschoolproject.bo.BOFactory;
import lk.ijse.elitedrivingschoolproject.bo.BOTypes;
import lk.ijse.elitedrivingschoolproject.bo.custom.LessonsBO;
import lk.ijse.elitedrivingschoolproject.dto.CourseDTO;
import lk.ijse.elitedrivingschoolproject.dto.LessonsDTO;
import lk.ijse.elitedrivingschoolproject.dto.tm.LessonsTM;

import java.net.URL;
import java.sql.Time;
import java.time.LocalDate;
import java.util.Optional;
import java.util.ResourceBundle;

public class LessonsPageController implements Initializable {

    @FXML
    private Button addLessonbtn;

    @FXML
    private TableColumn<LessonsTM, String> colAction;

    @FXML
    private TableColumn<LessonsTM, String> colCourseId;

    @FXML
    private TableColumn<LessonsTM, Time> colEndTime;

    @FXML
    private TableColumn<LessonsTM, String> colInstructorId;

    @FXML
    private TableColumn<LessonsTM, LocalDate> colLessonDate;

    @FXML
    private TableColumn<LessonsTM, Time> colStartTime;

    @FXML
    private TableColumn<LessonsTM, String> colStatus;

    @FXML
    private TableColumn<LessonsTM, String> colStudentId;

    @FXML
    private TableColumn<LessonsTM, String> collessonId;

    @FXML
    private TableView<LessonsTM> lessontbl;

    @FXML
    private TextField searchtxt;

    private final LessonsBO lessonsBO = (LessonsBO) BOFactory.getInstance().getBO(BOTypes.LESSONS);

    public void addLessonbtnOnAction(ActionEvent actionEvent) {

        try {
            FXMLLoader fxmlLoader= new FXMLLoader(getClass().getResource("/views/addLessonForm.fxml"));
            Parent parent = fxmlLoader.load();

            Stage stage = new Stage();
            stage.setTitle("Add Lesson");
            stage.setScene(new Scene(parent));
            stage.showAndWait();

            loadAllLessons();

        }catch (Exception e){
            e.printStackTrace();
            new Alert(Alert.AlertType.ERROR, "Something went wrong, please try again").show();
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        collessonId.setCellValueFactory(new PropertyValueFactory<>("lessonId"));
        colStudentId.setCellValueFactory(new PropertyValueFactory<>("studentId"));
        colCourseId.setCellValueFactory(new PropertyValueFactory<>("courseId"));
        colInstructorId.setCellValueFactory(new PropertyValueFactory<>("instructorId"));
        colLessonDate.setCellValueFactory(new PropertyValueFactory<>("lessonDate"));
        colStartTime.setCellValueFactory(new PropertyValueFactory<>("startTime"));
        colEndTime.setCellValueFactory(new PropertyValueFactory<>("endTime"));
        colStatus.setCellValueFactory(new PropertyValueFactory<>("status"));
        colAction.setCellValueFactory(new PropertyValueFactory<>("action"));

        try {
            loadAllLessons();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    private void loadAllLessons() {

        try {
            lessontbl.setItems(FXCollections.observableArrayList(
                    lessonsBO.getAllLessons().stream().map(lessonsDTO -> {

                        Pane action = new Pane();

                        Button btnEdit = new Button("✏");
                        btnEdit.setStyle("-fx-background-color: #4CAF50; -fx-text-fill: white; -fx-font-weight: bold;");
                        btnEdit.setPrefWidth(30);
                        btnEdit.setLayoutX(40);
                        btnEdit.setCursor(javafx.scene.Cursor.HAND);
                        btnEdit.setOnAction(event -> onUpdate(lessonsDTO));

                        Button btnDelete = new Button("🗑");
                        btnDelete.setStyle("-fx-background-color: #f44336; -fx-text-fill: white; -fx-font-weight: bold;");
                        btnDelete.setPrefWidth(30);
                        btnDelete.setLayoutX(0);
                        btnDelete.setCursor(javafx.scene.Cursor.HAND);
                        btnDelete.setOnAction(event -> onDelete(lessonsDTO.getLessonId()));

                        action.getChildren().addAll(btnEdit, btnDelete);
                        return new LessonsTM(
                                lessonsDTO.getLessonId(),
                                lessonsDTO.getStudentId(),
                                lessonsDTO.getCourseId(),
                                lessonsDTO.getInstructorId(),
                                lessonsDTO.getLessonDate(),
                                lessonsDTO.getStartTime(),
                                lessonsDTO.getEndTime(),
                                lessonsDTO.getStatus(),
                                action
                        );
                    }).toList()
            ));
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public void onDelete(String id){

        Alert alert = new Alert(Alert.AlertType.CONFIRMATION,
                "Are you sure?",
                ButtonType.YES,
                ButtonType.NO
        );
        alert.setTitle("Delete Lesson");
        Optional<ButtonType> result = alert.showAndWait();

        if (result.isPresent() && result.get() == ButtonType.YES) {

            try {
                boolean isDeleted = lessonsBO.deleteLesson(id);
                if (isDeleted) {
                    new Alert(Alert.AlertType.INFORMATION,"Lesson deleted Successfully!!").show();
                    loadAllLessons();
                }else {
                    new Alert(Alert.AlertType.ERROR,"Lesson could not be deleted!!").show();
                }
            }catch (Exception e){
                e.printStackTrace();
                new Alert(Alert.AlertType.ERROR, "Error while deleting Lesson: " + e.getMessage()).show();
            }
        }
    }

    public void onUpdate(LessonsDTO lessonsDTO){

        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/views/addLessonForm.fxml"));
            Parent parent = fxmlLoader.load();

            AddLessonPageController controller = fxmlLoader.getController();
            controller.loadData(lessonsDTO);

            Stage stage = new Stage();
            stage.setTitle("Update Lesson");
            stage.setScene(new Scene(parent));
            stage.showAndWait();

            loadAllLessons();

        }catch (Exception e){
            new Alert(Alert.AlertType.ERROR, e.getMessage(), ButtonType.OK).show();
        }
    }
}
