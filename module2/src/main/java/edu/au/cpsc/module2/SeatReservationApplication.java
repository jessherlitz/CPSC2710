package edu.au.cpsc.module2;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;
import javafx.application.Platform;

public class SeatReservationApplication extends Application {

    private SeatReservation seatReservation;
    @Override
    public void start(Stage stage) {

        seatReservation = new SeatReservation();
        seatReservation.setFlightDesignator("12345");
        seatReservation.setFirstName("First name");
        seatReservation.setLastName("Last name");

        Label flightDesignatorLabel = new Label("Flight Designator:");
        TextField flightDesignatorField = new TextField();
        flightDesignatorField.setText(seatReservation.getFlightDesignator());

        Label firstNameLabel = new Label("First Name:");
        TextField firstNameField = new TextField();
        firstNameField.setText(seatReservation.getFirstName());

        Label lastNameLabel = new Label("Last Name:");
        TextField lastNameField = new TextField();
        lastNameField.setText(seatReservation.getLastName());

        Label bagsLabel = new Label("Bags:");
        TextField bagsField = new TextField();
        bagsField.setText(String.valueOf(seatReservation.getNumberOfBags()));

        Label flightDateLabel = new Label("Flight Date:");
        DatePicker flightDatePicker = new DatePicker();
        flightDatePicker.setValue(seatReservation.getFlightDate());

        Label numPassengersLabel = new Label("Number of Passengers:");
        TextField numPassengersField = new TextField("1");
        numPassengersField.setEditable(false);

        CheckBox infantCheckBox = new CheckBox();
        Label flyingWithInfantLabel = new Label("Flying with Infant:");
        infantCheckBox.setSelected(seatReservation.isFlyingWithInfant());
        infantCheckBox.setOnAction(event -> {
            if (infantCheckBox.isSelected()) {
                numPassengersField.setText("2");
            } else {
                numPassengersField.setText("1");
            }
        });

        Button saveButton = new Button("Save");
        saveButton.setOnAction(event -> {
            try {
                seatReservation.setFlightDesignator(flightDesignatorField.getText());
                seatReservation.setFlightDate(flightDatePicker.getValue());
                seatReservation.setFirstName(firstNameField.getText());
                seatReservation.setLastName(lastNameField.getText());
                seatReservation.setNumberOfBags(Integer.parseInt(bagsField.getText()));

                if (infantCheckBox.isSelected()) {
                    seatReservation.makeFlyingWithInfant();
                } else {
                    seatReservation.makeNotFlyingWithInfant();
                }

                System.out.println(seatReservation);
                Platform.exit();

            } catch (IllegalArgumentException e) {
                System.out.println("Error: " + e.getMessage());
            }
        });

        Button cancelButton = new Button("Cancel");
        cancelButton.setOnAction(event -> {
            System.out.println("Cancel clicked");
            Platform.exit();
        });

        GridPane gridPane = new GridPane();
        gridPane.setPadding(new Insets(10));
        gridPane.setHgap(10);
        gridPane.setVgap(5);

        gridPane.add(flightDesignatorLabel, 0, 0);
        gridPane.add(flightDesignatorField, 1, 0);
        gridPane.add(flightDateLabel, 0, 1);
        gridPane.add(flightDatePicker, 1, 1);
        gridPane.add(firstNameLabel, 0, 2);
        gridPane.add(firstNameField, 1, 2);
        gridPane.add(lastNameLabel, 0, 3);
        gridPane.add(lastNameField, 1, 3);
        gridPane.add(bagsLabel, 0, 4);
        gridPane.add(bagsField, 1, 4);
        gridPane.add(flyingWithInfantLabel, 0, 5);
        gridPane.add(infantCheckBox, 1, 5);
        gridPane.add(numPassengersLabel, 0, 6);
        gridPane.add(numPassengersField, 1, 6);

        HBox buttonBox = new HBox(10);
        buttonBox.setPadding(new Insets(10));
        buttonBox.setAlignment(Pos.TOP_RIGHT);
        buttonBox.getChildren().addAll(cancelButton, saveButton);

        BorderPane root = new BorderPane();
        root.setCenter(gridPane);
        root.setBottom(buttonBox);

        Scene scene = new Scene(root, 450, 300);
        stage.setTitle("Seat Reservation Project");
        stage.setScene(scene);
        stage.show();
    }
}