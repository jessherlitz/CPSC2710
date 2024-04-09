package edu.au.cpsc.partii.module6partii.uimodel;

import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class ScheduledFlightModel {
    private final StringProperty flightDesignator;
    private final StringProperty arrivalAirportID;
    private final StringProperty departureAirportID;
    private final BooleanProperty modifiedProperty;
    private final BooleanProperty newProperty;
    private final BooleanProperty isValid;

    public ScheduledFlightModel() {
        flightDesignator = new SimpleStringProperty();
        arrivalAirportID = new SimpleStringProperty();
        departureAirportID = new SimpleStringProperty();
        modifiedProperty = new SimpleBooleanProperty();
        newProperty = new SimpleBooleanProperty();
        isValid = new SimpleBooleanProperty();

        flightDesignator.addListener((observable, oldValue, newValue) -> {
            modifiedProperty.set(true);
            validate();
        });
        arrivalAirportID.addListener((observable, oldValue, newValue) -> {
            modifiedProperty.set(true);
            validate();
        });
        departureAirportID.addListener((observable, oldValue, newValue) -> {
            modifiedProperty.set(true);
            validate();
        });

    }

    public StringProperty flightDesignator() {
        return flightDesignator;
    }

    public StringProperty arrivalAirportID() {
        return arrivalAirportID;
    }

    public StringProperty departureAirportID() {
        return departureAirportID;
    }

    public BooleanProperty newProperty() {
        return newProperty;
    }

    public BooleanProperty modifiedProperty() {
        return modifiedProperty;
    }

    public BooleanProperty isValid() { return isValid; }

    public boolean isFlightDesignatorValid() {
        return !flightDesignator.get().isEmpty();
    }

    public boolean isArrivalAirportIDValid() {
        return !arrivalAirportID.get().isEmpty();
    }

    public boolean isDepartureAirportIDValid() {
        return !departureAirportID.get().isEmpty();
    }

    public void validate() {

        if (newProperty.get()) {
            isValid.set(isFlightDesignatorValid() && isArrivalAirportIDValid() && isDepartureAirportIDValid());
        } else {
            isValid.set(isFlightDesignatorValid() && isArrivalAirportIDValid() && isDepartureAirportIDValid() && modifiedProperty.get());
        }
    }

}
