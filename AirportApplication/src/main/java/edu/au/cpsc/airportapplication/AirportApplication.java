package edu.au.cpsc.airportapplication;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class AirportApplication extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(AirportApplication.class.getResource("AirportApplication.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 700, 500);
        stage.setMinWidth(700);
        stage.setMinHeight(500);
        stage.setTitle("Airport Terminal");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}