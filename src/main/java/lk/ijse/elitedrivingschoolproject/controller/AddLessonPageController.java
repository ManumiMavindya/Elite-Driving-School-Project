package lk.ijse.elitedrivingschoolproject.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;

public class AddLessonPageController {

    @FXML
    private Button addLessonbtn;

    @FXML
    private ComboBox<?> coursecmb;

    @FXML
    private ComboBox<?> endcmb;

    @FXML
    private ComboBox<?> instructorcmb;

    @FXML
    private DatePicker lessonDatepicker;

    @FXML
    private Label lessonIdlbl;

    @FXML
    private ComboBox<?> startcmb;

    @FXML
    private ComboBox<?> statuscmb;

    @FXML
    private ComboBox<?> studentcmb;

    @FXML
    void addLessonbtnOnAction(ActionEvent event) {

    }

}
