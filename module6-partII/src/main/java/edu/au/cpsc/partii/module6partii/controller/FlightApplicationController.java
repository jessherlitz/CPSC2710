package edu.au.cpsc.partii.module6partii.controller;

import edu.au.cpsc.partii.module6partii.data.Db;
import edu.au.cpsc.partii.module6partii.model.ScheduledFlight;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.beans.property.BooleanProperty;

public class FlightApplicationController {

    @FXML
    private FlightDetailViewController flightDetailViewController;

    @FXML
    private FlightTableViewController flightTableViewController;

    @FXML
    private Button updateButton, deleteButton;

    private ScheduledFlight flightBeingEdited;

    private BooleanProperty canDeleteFlight;

    public void initialize() {
        flightTableViewController.showFlights(Db.getDatabase().getScheduledFlights());
        canDeleteFlight = new SimpleBooleanProperty(false);

        flightTableViewController.onFlightSelectionChanged(
                event -> {
                    showFlight(event.getSelectedFlight());

                    if (event.getSelectedFlight() == null) {
                        canDeleteFlight.set(false);
                    } else {
                        canDeleteFlight.set(true);
                    }
                });

        updateButton.disableProperty().bind(flightDetailViewController.getModel().isValid().not());
        deleteButton.disableProperty().bind(canDeleteFlight.not());

        showFlight(null);
    }

    private void showFlight(ScheduledFlight flight) {
        flightDetailViewController.showFlight(flight);
        flightBeingEdited = flight == null ? new ScheduledFlight() : flight;

        String buttonLabel = flightDetailViewController.getModel().newProperty().get() ? "Add" : "Update";
        updateButton.setText(buttonLabel);
    }

    @FXML
    protected void updateButtonAction() {
        flightDetailViewController.updateFlight(flightBeingEdited);
        if (flightDetailViewController.getModel().newProperty().get()) {
            System.out.println("SAVING TO DB: " + flightBeingEdited);

            if (!flightDetailViewController.getModel().isValid().get()) {
                return;
            }

            Db.getDatabase().addScheduledFlight(flightBeingEdited);

        } else {
            Db.getDatabase().updateScheduledFlight(flightBeingEdited);
        }
        Db.saveDatabase();

        flightTableViewController.showFlights(Db.getDatabase().getScheduledFlights());
        flightTableViewController.select(flightBeingEdited);
    }
    @FXML
    public void deleteButtonAction() {
        if (flightBeingEdited == null) {
            return;
        }

        Db.getDatabase().removeScheduledFlight(flightBeingEdited);
        Db.saveDatabase();
        flightTableViewController.showFlights(Db.getDatabase().getScheduledFlights());

    }
}