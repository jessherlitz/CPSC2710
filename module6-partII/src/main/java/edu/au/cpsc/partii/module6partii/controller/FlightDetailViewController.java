package edu.au.cpsc.partii.module6partii.controller;

import edu.au.cpsc.partii.module6partii.model.ScheduledFlight;
import edu.au.cpsc.partii.module6partii.uimodel.ScheduledFlightModel;
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

    private ScheduledFlightModel model;

    public ScheduledFlightModel getModel() {
        return model;
    }


    public void initialize() {
        model = new ScheduledFlightModel();
        flightDesignator.textProperty().bindBidirectional(model.flightDesignator());
        arrivalAirportID.textProperty().bindBidirectional(model.arrivalAirportID());
        departureAirportID.textProperty().bindBidirectional(model.departureAirportID());

        flightDesignator.textProperty().addListener((observable, oldValue, newValue) -> {
            if (model.isFlightDesignatorValid()) {
                flightDesignator.getStyleClass().remove("error");
            } else {
                flightDesignator.getStyleClass().add("error");
            }
        });

        arrivalAirportID.textProperty().addListener((observable, oldValue, newValue) -> {
            if (model.isArrivalAirportIDValid()) {
                arrivalAirportID.getStyleClass().remove("error");
            } else {
                arrivalAirportID.getStyleClass().add("error");
            }
        });

        departureAirportID.textProperty().addListener((observable, oldValue, newValue) -> {
            if (model.isDepartureAirportIDValid()) {
                departureAirportID.getStyleClass().remove("error");
            } else {
                departureAirportID.getStyleClass().add("error");
            }
        });
    }

    public void showFlight(ScheduledFlight sf) {
        ToggleButton[] toggleButtons = {U, M, T, W, R, F, S};

        if (sf == null) {
            model.flightDesignator().set("");
            model.arrivalAirportID().set("");
            model.departureAirportID().set("");
            model.modifiedProperty().set(false);

            model.newProperty().set(true);
            model.validate();

            for (ToggleButton t : toggleButtons) {
                t.setSelected(false);
            }

            return;
        }

        model.flightDesignator().set(sf.getFlightDesignator());
        model.arrivalAirportID().set(sf.getArrivalAirportIdent());
        model.departureAirportID().set(sf.getDepartureAirportIdent());

        model.newProperty().set(false);
        model.modifiedProperty().set(false);
        model.validate();

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
        sf.setFlightDesignator(model.flightDesignator().get());
        sf.setArrivalAirportIdent(model.arrivalAirportID().get());
        sf.setDepartureAirportIdent(model.departureAirportID().get());

        model.validate();


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
