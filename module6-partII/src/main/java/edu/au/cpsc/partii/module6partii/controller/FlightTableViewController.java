package edu.au.cpsc.partii.module6partii.controller;

import edu.au.cpsc.partii.module6partii.model.ScheduledFlight;
import javafx.collections.FXCollections;
import javafx.collections.transformation.SortedList;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.event.EventType;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

import java.util.List;

public class FlightTableViewController {

    @FXML
    private TableView<ScheduledFlight> flightTableView;

    @FXML
    private TableColumn<ScheduledFlight, String> flightDesignatorColumn, departureAirportIDColumn, arrivalAirportIDColumn, daysOfWeekColumn;

    public void initialize() {
        flightDesignatorColumn.setCellValueFactory(new PropertyValueFactory<ScheduledFlight, String>("flightDesignator"));
        departureAirportIDColumn.setCellValueFactory(new PropertyValueFactory<ScheduledFlight, String>("departureAirportIdent"));
        arrivalAirportIDColumn.setCellValueFactory(new PropertyValueFactory<ScheduledFlight, String>("arrivalAirportIdent"));
        daysOfWeekColumn.setCellValueFactory(new PropertyValueFactory<ScheduledFlight, String>("daysOfWeekFormatted"));
        flightTableView.getSelectionModel().selectedItemProperty().addListener(c -> tableSelectionChanged());
    }

    public void showFlights(List<ScheduledFlight> flights) {

        SortedList<ScheduledFlight> sortedList = new SortedList<>(FXCollections.observableList(flights));
        flightTableView.setItems(sortedList);

        sortedList.comparatorProperty().bind(flightTableView.comparatorProperty());
        flightTableView.refresh();
    }

    public void select(ScheduledFlight flight) {
        flightTableView.getSelectionModel().select(flight);
    }

    private void tableSelectionChanged() {
        ScheduledFlight selectedFlight = flightTableView.getSelectionModel().getSelectedItem();
        FlightTableEvent event = new FlightTableEvent(FlightTableEvent.FLIGHT_SELECTED, selectedFlight);

     ///

        flightTableView.fireEvent(event);
    }

    public void onFlightSelectionChanged(EventHandler<FlightTableEvent> handler) {
        flightTableView.addEventHandler(FlightTableEvent.FLIGHT_SELECTED, handler);
    }


    public static class FlightTableEvent extends Event {

        public static final EventType<FlightTableEvent> ANY = new EventType<>(Event.ANY, "ANY");

        public static final EventType<FlightTableEvent> FLIGHT_SELECTED = new EventType<>(ANY, "FLIGHT_SELECTED");

        private ScheduledFlight selectedFlight;

        public FlightTableEvent(EventType<? extends Event> eventType, ScheduledFlight selectedFlight) {
            super(eventType);
            this.selectedFlight = selectedFlight;
        }

        public ScheduledFlight getSelectedFlight() {
            return selectedFlight;
        }
    }
}
