package lk.ijse.elitedrivingschoolproject.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import lk.ijse.elitedrivingschoolproject.bo.BOFactory;
import lk.ijse.elitedrivingschoolproject.bo.BOTypes;
import lk.ijse.elitedrivingschoolproject.bo.custom.LessonsBO;
import lk.ijse.elitedrivingschoolproject.dto.CourseDTO;
import lk.ijse.elitedrivingschoolproject.dto.LessonsDTO;

import java.net.URL;
import java.sql.Time;
import java.time.LocalDate;
import java.util.ResourceBundle;

public class AddLessonPageController implements Initializable {

    @FXML
    private Button addLessonbtn;

    @FXML
    private ComboBox<String> coursecmb;

    @FXML
    private ComboBox<Time> endcmb;

    @FXML
    private ComboBox<String> instructorcmb;

    @FXML
    private DatePicker lessonDatepicker;

    @FXML
    private Label lessonIdlbl;

    @FXML
    private ComboBox<Time> startcmb;

    @FXML
    private ComboBox<String> statuscmb;

    @FXML
    private ComboBox<String> studentcmb;

    @FXML
    private Button updateLessonbtn;

     LessonsBO lessonsBO = (LessonsBO) BOFactory.getInstance().getBO(BOTypes.LESSONS) ;

    @FXML
    void addLessonbtnOnAction(ActionEvent event) {

        String studentId = startcmb.getValue().toString();
        String courseId = coursecmb.getValue().toString();
        String instructorId = instructorcmb.getValue().toString();
        LocalDate date = LocalDate.parse(lessonDatepicker.getValue().toString());
        Time start = startcmb.getValue();
        Time end = endcmb.getValue();
        String status = statuscmb.getValue().toString();

        if (studentId == null || courseId == null || instructorId == null || date == null || start == null || end == null || status == null) {
            addLessonbtn.setDisable(true);
            new Alert(Alert.AlertType.ERROR, "Please fill all the fields", ButtonType.OK).show();
            return;
        }

        try {
            boolean isSaved = lessonsBO.saveLesson(LessonsDTO.builder()
            .lessonId(lessonIdlbl.getText())
                    .studentId(studentId)
                    .courseId(courseId)
                    .instructorId(instructorId)
                    .lessonDate(date)
                    .startTime(start)
                    .endTime(end)
                    .status(status)
                    .build());

                 if (isSaved) {
                     new Alert(Alert.AlertType.INFORMATION, "Lesson saved successfully!!", ButtonType.OK).show();
                 }else {
                     new Alert(Alert.AlertType.ERROR, "Lesson not saved successfully!!", ButtonType.OK).show();
                 }
        }catch (Exception e) {
            throw new RuntimeException(e);
        }

    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        try {
            lessonIdlbl.setText(lessonsBO.generateNewLessonId());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public void loadData(LessonsDTO lessonsDTO) {

        lessonIdlbl.setText(lessonsDTO.getLessonId());
        studentcmb.setValue(lessonsDTO.getStudentId());
        coursecmb.setValue(lessonsDTO.getCourseId());
        instructorcmb.setValue(lessonsDTO.getInstructorId());
        lessonDatepicker.setValue(lessonsDTO.getLessonDate());
        startcmb.setValue(lessonsDTO.getStartTime());
        endcmb.setValue(lessonsDTO.getEndTime());
        statuscmb.setValue(lessonsDTO.getStatus());

    }


    public void updateLessonbtnOnAction(ActionEvent actionEvent) {

        String studentId = startcmb.getValue().toString();
        String courseId = coursecmb.getValue().toString();
        String instructorId = instructorcmb.getValue().toString();
        LocalDate date = LocalDate.parse(lessonDatepicker.getValue().toString());
        Time start = startcmb.getValue();
        Time end = endcmb.getValue();
        String status = statuscmb.getValue().toString();

        if (studentId == null || courseId == null || instructorId == null || date == null || start == null || end == null || status == null) {
            updateLessonbtn.setDisable(true);
            new Alert(Alert.AlertType.ERROR, "Please fill all the fields", ButtonType.OK).show();
            return;
        }

        try {
            boolean isUpdated = lessonsBO.updateLesson(LessonsDTO.builder()
                    .lessonId(lessonIdlbl.getText())
                    .studentId(studentId)
                    .courseId(courseId)
                    .instructorId(instructorId)
                    .lessonDate(date)
                    .startTime(start)
                    .endTime(end)
                    .status(status)
                    .build());

            if (isUpdated) {
                new Alert(Alert.AlertType.INFORMATION, "Lesson updated successfully!!", ButtonType.OK).show();
            }else {
                new Alert(Alert.AlertType.ERROR, "Lesson not update successfully!!", ButtonType.OK).show();
            }
        }catch (Exception e) {
            throw new RuntimeException(e);
        }


    }
}
