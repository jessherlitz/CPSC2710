package au.edu.cpsc.module7;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class NotesApp extends Application {
    @Override
    public void start(Stage stage) throws Exception {
        FXMLLoader fxmlLoader = new FXMLLoader(NotesApp.class.getResource("main.fxml"));

        Scene scene = new Scene(fxmlLoader.load(), 700, 600);

        stage.setResizable(false);
        stage.setMinWidth(700);
        stage.setMinHeight(600);
        stage.setMaxWidth(700);
        stage.setMinHeight(600);
        stage.setTitle("Notes App");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}