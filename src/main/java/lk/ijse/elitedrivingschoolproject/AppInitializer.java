package lk.ijse.elitedrivingschoolproject;

import javafx.application.Application;
import javafx.concurrent.Task;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class AppInitializer extends Application {
    public static void main(String[] args) {

        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        primaryStage.setScene(new Scene(
                new FXMLLoader(getClass().getResource("views/addStudentForm.fxml")).load()
        ));
        primaryStage.show();

        Task<Scene> loadingTask = new Task<>() {
            @Override
            protected Scene call() throws Exception {
                FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("views/mainDashboard.fxml"));
                return new Scene(fxmlLoader.load());
            }
        };

        loadingTask.setOnSucceeded(event -> {
            Scene value = loadingTask.getValue();

            primaryStage.setTitle("Dashboard");
            primaryStage.setScene(value);
        });

        loadingTask.setOnFailed(event -> {
            System.out.println("Fail to load application");
        });

        new Thread(loadingTask).start();
    }
}
