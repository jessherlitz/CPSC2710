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
    private BorderPane root;
    private TextField flightDesignatorField;
    private TextField firstNameField;
    private TextField lastNameField;
    private TextField bagsField;
    private DatePicker flightDatePicker;
    private TextField numPassengersField;
    private CheckBox infantCheckBox;

    @Override
    public void start(Stage stage) {
        seatReservation = new SeatReservation();
        seatReservation.setFlightDesignator("12345");
        seatReservation.setFirstName("First name");
        seatReservation.setLastName("Last name");

        root = new BorderPane();
        initializeComponents();
        setUpEventHandlers();
        setUpLayout();

        Scene scene = new Scene(root, 450, 300);
        stage.setTitle("Seat Reservation Project");
        stage.setScene(scene);
        stage.show();
    }

    private void initializeComponents() {
        flightDesignatorField = new TextField();
        firstNameField = new TextField();
        lastNameField = new TextField();
        bagsField = new TextField();
        flightDatePicker = new DatePicker();
        numPassengersField = new TextField("1");
        infantCheckBox = new CheckBox();

        flightDesignatorField.setText(seatReservation.getFlightDesignator());
        firstNameField.setText(seatReservation.getFirstName());
        lastNameField.setText(seatReservation.getLastName());
        bagsField.setText(String.valueOf(seatReservation.getNumberOfBags()));
        flightDatePicker.setValue(seatReservation.getFlightDate());
        numPassengersField.setEditable(false);
        infantCheckBox.setSelected(seatReservation.isFlyingWithInfant());
    }

    private void setUpEventHandlers() {
        infantCheckBox.setOnAction(event -> {
            if (infantCheckBox.isSelected()) {
                numPassengersField.setText("2");
            } else {
                numPassengersField.setText("1");
            }
        });
    }

    private void setUpLayout() {
        GridPane gridPane = new GridPane();
        gridPane.setPadding(new Insets(10));
        gridPane.setHgap(10);
        gridPane.setVgap(5);

        gridPane.addRow(0, new Label("Flight Designator:"), flightDesignatorField);
        gridPane.addRow(1, new Label("Flight Date:"), flightDatePicker);
        gridPane.addRow(2, new Label("First Name:"), firstNameField);
        gridPane.addRow(3, new Label("Last Name:"), lastNameField);
        gridPane.addRow(4, new Label("Bags:"), bagsField);
        gridPane.addRow(5, new Label("Flying with Infant:"), infantCheckBox);
        gridPane.addRow(6, new Label("Number of Passengers:"), numPassengersField);

        HBox buttonBox = new HBox(10);
        buttonBox.setPadding(new Insets(10));
        buttonBox.setAlignment(Pos.TOP_RIGHT);
        Button saveButton = new Button("Save");
        Button cancelButton = new Button("Cancel");
        saveButton.setOnAction(event -> saveSeatReservation());
        cancelButton.setOnAction(event -> Platform.exit());
        buttonBox.getChildren().addAll(cancelButton, saveButton);

        root.setCenter(gridPane);
        root.setBottom(buttonBox);
    }

    private void saveSeatReservation() {
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
    }
}
