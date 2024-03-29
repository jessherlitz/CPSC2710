package edu.au.cpsc.module4.controller;

import edu.au.cpsc.module4.model.ScheduledFlight;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleButton;

import java.util.HashSet;
import java.util.Set;


public class FlightDetailViewController {

    @FXML
    private TextField flightDesignator, arrivalAirportID, departureAirportID;

    @FXML
    private ToggleButton U, M, T, W, R, F, S;

    public void showFlight(ScheduledFlight sf) {
        ToggleButton[] toggleButtons = {U, M, T, W, R, F, S};

        if (sf == null) {
            flightDesignator.clear();
            arrivalAirportID.clear();
            departureAirportID.clear();


            for (ToggleButton t : toggleButtons) {
                t.setSelected(false);
            }

            return;
        }

        flightDesignator.setText(sf.getFlightDesignator());
        arrivalAirportID.setText(sf.getArrivalAirportIdent());
        departureAirportID.setText(sf.getDepartureAirportIdent());

        for (ToggleButton t : toggleButtons) {
            Node source = t;
            String id = source.getId();

            if (sf.getDaysOfWeek().contains(id)) {
                t.setSelected(true);
            } else {
                t.setSelected(false);
            }
        }
    }

    public void updateFlight(ScheduledFlight sf) {
        sf.setFlightDesignator(flightDesignator.getText());
        sf.setArrivalAirportIdent(arrivalAirportID.getText());
        sf.setDepartureAirportIdent(departureAirportID.getText());

        Set<String> toggleSet = new HashSet<>();
        ToggleButton[] toggleButtons = {U, M, T, W, R, F, S};

        for (ToggleButton t : toggleButtons) {
            if (t.isSelected()) {
                Node source = t;
                String id = source.getId();
                toggleSet.add(id);
            }
        }

        sf.setDaysOfWeek(toggleSet);
    }
}
