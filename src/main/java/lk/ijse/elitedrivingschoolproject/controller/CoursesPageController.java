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
import lk.ijse.elitedrivingschoolproject.bo.custom.CourseBO;
import lk.ijse.elitedrivingschoolproject.dto.CourseDTO;
import lk.ijse.elitedrivingschoolproject.dto.tm.CourseTM;
import lk.ijse.elitedrivingschoolproject.dto.tm.InstructorTM;

import java.io.IOException;
import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;

public class CoursesPageController implements Initializable {

    @FXML
    private Button addCoursebtn;

    @FXML
    private TableColumn<CourseTM, String> colAction;

    @FXML
    private TableColumn<CourseTM, String> colCourseId;

    @FXML
    private TableColumn<CourseTM, String> colCourseName;

    @FXML
    private TableColumn<CourseTM, String> colDescription;

    @FXML
    private TableColumn<CourseTM, String> colDuration;

    @FXML
    private TableColumn<CourseTM, Double> colFee;

    @FXML
    private TableView<CourseTM> coursetbl;

    @FXML
    private TextField searchtxt;

    private final CourseBO courseBO = (CourseBO) BOFactory.getInstance().getBO(BOTypes.COURSE);

    @FXML
    public void addCoursebtn(ActionEvent event) {

        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/views/addCourseForm.fxml"));
            Parent parent = loader.load();

            Stage stage = new Stage();
            stage.setTitle("Add Course");
            stage.setScene(new Scene(parent));
            stage.showAndWait();

            loadAllCourses();

        } catch (IOException e) {
            e.printStackTrace();
            new Alert(Alert.AlertType.ERROR, e.getMessage(), ButtonType.OK).show();
        }

    }

    private void loadAllCourses() {

        try {
            coursetbl.setItems(FXCollections.observableArrayList(
                    courseBO.getAllCourses().stream().map(courseDTO -> {

                        Pane action = new Pane();

                        Button editbtn = new Button("✏");
                        editbtn.setStyle("-fx-background-color: #4CAF50; -fx-text-fill: white; -fx-font-weight: bold;");
                        editbtn.setPrefWidth(30);
                        editbtn.setLayoutX(40);
                        editbtn.setCursor(javafx.scene.Cursor.HAND);
                        editbtn.setOnAction(event -> onUpdate(courseDTO));

                        Button deletebtn = new Button("🗑");
                        deletebtn.setStyle("-fx-background-color: #f44336; -fx-text-fill: white; -fx-font-weight: bold;");
                        deletebtn.setPrefWidth(30);
                        deletebtn.setLayoutX(0);
                        deletebtn.setCursor(javafx.scene.Cursor.HAND);
                        deletebtn.setOnAction(event -> onDelete(courseDTO.getCourseId()));

                        action.getChildren().addAll(editbtn, deletebtn);
                        return new CourseTM(
                                courseDTO.getCourseId(),
                                courseDTO.getCourseName(),
                                courseDTO.getDuration(),
                                courseDTO.getFee(),
                                courseDTO.getDescription(),
                                action
                        );
                    }).toList()
            ));
        } catch (Exception e) {
                throw new RuntimeException(e);
        }

    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        colCourseId.setCellValueFactory(new PropertyValueFactory<>("courseId"));
        colCourseName.setCellValueFactory(new PropertyValueFactory<>("courseName"));
        colDuration.setCellValueFactory(new PropertyValueFactory<>("duration"));
        colFee.setCellValueFactory(new PropertyValueFactory<>("fee"));
        colDescription.setCellValueFactory(new PropertyValueFactory<>("description"));
        colAction.setCellValueFactory(new PropertyValueFactory<>("action"));

        try {
            loadAllCourses();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public void onUpdate(CourseDTO courseDTO) {

        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/views/addCourseForm.fxml"));
            Parent parent = loader.load();

            AddCoursePageController controller = loader.getController();
            controller.loadData(courseDTO);

            Stage stage = new Stage();
            stage.setTitle("Update Course");
            stage.setScene(new Scene(parent));
            stage.showAndWait();

            loadAllCourses();

        } catch (IOException e) {
            new Alert(Alert.AlertType.ERROR, e.getMessage(), ButtonType.OK).show();
        }
    }

    public void onDelete(String id) {

        Alert alert = new Alert(Alert.AlertType.CONFIRMATION,
                "Are you sure?",
                ButtonType.YES,
                ButtonType.NO);

        alert.setTitle("Delete Course");

        Optional<ButtonType> result = alert.showAndWait();

        if (result.isPresent() && result.get() == ButtonType.YES) {

            try {
                boolean isDeleted = courseBO.deleteCourse(id);
                if (isDeleted) {
                    new Alert(Alert.AlertType.INFORMATION,"Course Deleted successfully!!").show();
                    loadAllCourses();
                }else {
                    new Alert(Alert.AlertType.ERROR,"Course Not Deleted!!").show();
                }
            }catch (Exception e) {
                throw new RuntimeException(e);
            }
        }
    }

    public void onClickTable(MouseEvent mouseEvent) {

    }
}
