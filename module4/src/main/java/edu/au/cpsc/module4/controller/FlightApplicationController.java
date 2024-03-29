package edu.au.cpsc.module4.controller;

import edu.au.cpsc.module4.data.Db;
import edu.au.cpsc.module4.model.ScheduledFlight;
import javafx.fxml.FXML;
import javafx.scene.control.Button;

public class FlightApplicationController {

    @FXML
    private FlightDetailViewController flightDetailViewController;

    @FXML
    private FlightTableViewController flightTableViewController;

    @FXML
    private Button updateButton;

    private ScheduledFlight flightBeingEdited;

    private boolean flightBeingEditedIsNew;


    public void initialize() {
        flightTableViewController.showFlights(Db.getDatabase().getScheduledFlights());

        flightTableViewController.onFlightSelectionChanged(
                event -> showFlight(event.getSelectedFlight()));
        showFlight(null);
    }

    private void showFlight(ScheduledFlight flight) {
        flightDetailViewController.showFlight(flight);
        flightBeingEdited = flight == null ? new ScheduledFlight() : flight;
        flightBeingEditedIsNew = flight == null;

        String buttonLabel = flightBeingEditedIsNew ? "Add" : "Update";
        updateButton.setText(buttonLabel);
    }


    @FXML
    protected void updateButtonAction() {
        flightDetailViewController.updateFlight(flightBeingEdited);
        if (flightBeingEditedIsNew) {
            System.out.println(flightBeingEdited);

            Db.getDatabase().addScheduledFlight(flightBeingEdited);

        } else {
            Db.getDatabase().updateScheduledFlight(flightBeingEdited);
        }
        Db.saveDatabase();

        flightTableViewController.showFlights(Db.getDatabase().getScheduledFlights());
        flightTableViewController.select(flightBeingEdited);
    }

    public void deleteButtonAction() {
        if (flightBeingEdited == null) {
            return;
        }

        Db.getDatabase().removeScheduledFlight(flightBeingEdited);
        flightTableViewController.showFlights(Db.getDatabase().getScheduledFlights());

    }
}