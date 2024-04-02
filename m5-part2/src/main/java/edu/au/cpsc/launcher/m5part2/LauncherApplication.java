package edu.au.cpsc.launcher.m5part2;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class LauncherApplication extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(LauncherApplication.class.getResource("launcher-app.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 310, 340);
        stage.setTitle("Part II");
        stage.setResizable(false);
        stage.setMinHeight(340);
        stage.setMinWidth(310);
        stage.setMaxHeight(340);
        stage.setMaxWidth(310);
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}