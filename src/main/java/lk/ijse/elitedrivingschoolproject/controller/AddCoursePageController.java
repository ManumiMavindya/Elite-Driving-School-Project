package lk.ijse.elitedrivingschoolproject.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import lk.ijse.elitedrivingschoolproject.bo.BOFactory;
import lk.ijse.elitedrivingschoolproject.bo.BOTypes;
import lk.ijse.elitedrivingschoolproject.bo.custom.CourseBO;
import lk.ijse.elitedrivingschoolproject.dto.CourseDTO;

import java.net.URL;
import java.util.ResourceBundle;

public class AddCoursePageController implements Initializable {

    @FXML
    private TextField CourseFeetxt;

    @FXML
    private TextField Descriptiontxt;

    @FXML
    private Button addCoursebtn;

    @FXML
    private TextField courseDurationtxt;

    @FXML
    private TextField courseNametxt;

    @FXML
    private Label courselbl;

    @FXML
    private Button updateCoursebtn;

    CourseBO courseBO = (CourseBO) BOFactory.getInstance().getBO(BOTypes.COURSE) ;

    @FXML
    public void addCoursebtnOnAction(ActionEvent event) {

            String name = courseNametxt.getText();
            String duration = courseDurationtxt.getText();
            Double fee = Double.valueOf(CourseFeetxt.getText());
            String description = Descriptiontxt.getText();

            if (name.isEmpty() || duration.isEmpty() || description.isEmpty() || fee.isNaN() || fee.isInfinite()) {
                addCoursebtn.setDisable(true);
                new Alert(Alert.AlertType.ERROR, "Please fill all the required fields!").show();
                return;
            }

            try {
                boolean isSaved = courseBO.saveCourse(CourseDTO.builder()
                        .courseId(courselbl.getText())
                        .courseName(String.valueOf(courseNametxt))
                        .duration(duration)
                        .fee(fee)
                        .description(description)
                        .build());

                if (isSaved) {
                    new Alert(Alert.AlertType.INFORMATION, "Course added successfully!").show();
                }else {
                    new Alert(Alert.AlertType.ERROR, "Course could not be added!").show();
                }
            }catch (Exception e) {
                throw new RuntimeException(e);
            }

    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        try {
            courselbl.setText(courseBO.generateNewCourseId());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public void loadData(CourseDTO courseDTO) {

        courselbl.setText(courseDTO.getCourseId());
        courseNametxt.setText(courseDTO.getCourseName());
        courseDurationtxt.setText(courseDTO.getDuration());
        CourseFeetxt.setText(String.valueOf(courseDTO.getFee()));
        Descriptiontxt.setText(courseDTO.getDescription());

    }

    public void updateCoursebtnOnAction(ActionEvent actionEvent) {

        String name = courseNametxt.getText();
        String duration = courseDurationtxt.getText();
        Double fee = Double.valueOf(CourseFeetxt.getText());
        String description = Descriptiontxt.getText();

        if (name.isEmpty() || duration.isEmpty() || description.isEmpty() || fee.isNaN() || fee.isInfinite()) {
            updateCoursebtn.setDisable(true);
            new Alert(Alert.AlertType.ERROR, "Please fill all the required fields!").show();
            return;
        }

        try {
            boolean isUpdated = courseBO.updateCourse(CourseDTO.builder()
                    .courseId(courselbl.getText())
                    .courseName(String.valueOf(courseNametxt))
                    .duration(duration)
                    .fee(fee)
                    .description(description)
                    .build());

            if (isUpdated) {
                new Alert(Alert.AlertType.INFORMATION, "Course updated successfully!").show();
            }else {
                new Alert(Alert.AlertType.ERROR, "Course could not be update!").show();
            }
        }catch (Exception e) {
            throw new RuntimeException(e);
        }

    }



}

