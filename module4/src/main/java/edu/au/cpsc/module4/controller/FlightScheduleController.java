package edu.au.cpsc.module4.controller;

import edu.au.cpsc.module4.data.Database;
import edu.au.cpsc.module4.model.ScheduledFlight;
import javafx.collections.FXCollections;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;

import java.util.Set;

public class FlightScheduleController {

    @FXML
    private TableView<ScheduledFlight> tableView;

    @FXML
    private TableColumn<ScheduledFlight, String> flightDesignatorColumn, departureAirportIDColumn, arrivalAirportIDColumn, daysOfWeekColumn;

    @FXML
    private TextField flightDesignator, arrivalAirportID, daysOfWeek;


    public void initialize() {
        flightDesignatorColumn.setCellValueFactory(new PropertyValueFactory<ScheduledFlight, String>("flightDesignator"));
        departureAirportIDColumn.setCellValueFactory(new PropertyValueFactory<ScheduledFlight, String>("departureAirportIdent"));
        arrivalAirportIDColumn.setCellValueFactory(new PropertyValueFactory<ScheduledFlight, String>("arrivalAirportIdent"));
        daysOfWeekColumn.setCellValueFactory(new PropertyValueFactory<ScheduledFlight, String>("daysOfWeek"));


        Database dbTest = new Database();
        dbTest.addScheduledFlight(new ScheduledFlight("test1", "test2", "fdfdf"));
        dbTest.addScheduledFlight(new ScheduledFlight("tetr", "ddssds", "dsdsd"));
        dbTest.addScheduledFlight(new ScheduledFlight("dsdsd", "dsdsd", "dsdsd"));

        SortedList<ScheduledFlight> sortedList = new SortedList<>(FXCollections.observableList(dbTest.getScheduledFlights()));

        tableView.setItems(sortedList);
        sortedList.comparatorProperty().bind(tableView.comparatorProperty());
        tableView.getSelectionModel().selectedItemProperty().addListener(c -> tableSelectionChanged());
    }

    private void tableSelectionChanged() {
        ScheduledFlight selectedFlight = tableView.getSelectionModel().getSelectedItem();
        flightDesignator.setText(selectedFlight.getFlightDesignator());
        arrivalAirportID.setText(selectedFlight.getArrivalAirportIdent());
        daysOfWeek.setText(selectedFlight.getDaysOfWeek());
    }


    public void flightDesignatorOnChange(KeyEvent keyEvent) {
        System.out.println("ON CHANGE" + flightDesignator.getText());
        System.out.println(keyEvent.getSource());
        System.out.println(keyEvent.getText());
    }

    public void arrivalAirportIDOnChange(KeyEvent keyEvent) {
        System.out.println("ON CHANGE" + arrivalAirportID.getText());
        System.out.println(keyEvent.getSource());
        System.out.println(keyEvent.getText());

    }

    public void daysOfWeekOnChange(KeyEvent keyEvent) {
        System.out.println("ON CHANGE" + daysOfWeek.getText());
        System.out.println(keyEvent.getSource());
        System.out.println(keyEvent.getText());
    }

    public void toggleOnClick(MouseEvent mouseEvent) {
        System.out.println(mouseEvent.getSource());
    }

    public void buttonOnClick(MouseEvent mouseEvent) {
        System.out.println(mouseEvent.getSource());
    }
}