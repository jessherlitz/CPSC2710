package edu.au.cpsc.airportapplication;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;
import java.io.IOException;
import java.util.List;
public class AirportApplicationController  {

    @FXML
    private WebView webView;

    @FXML
    private TextField identTextField;

    @FXML
    private TextField iataCodeTextField;

    @FXML
    private TextField localCodeTextField;

    @FXML
    private TextField typeTextField;

    @FXML
    private TextField nameTextField;

    @FXML
    private TextField elevationTextField;

    @FXML
    private TextField countryTextField;

    @FXML
    private TextField regionTextField;

    @FXML
    private TextField municipalityTextField;

    private List<Airport> airportList;

    public AirportApplicationController() throws IOException {
        try {
            airportList = Airport.readAll();
        } catch (IOException e) {
            System.out.println("There was an error reading the file. " + e.getStackTrace());
        }
    }

    private void searchAirport() {
        String ident = identTextField.getText();
        String iataCode = iataCodeTextField.getText();
        String localCode = localCodeTextField.getText();

        if (!ident.isEmpty()) {
            if (findAirportByID(ident.trim())) return;
        }

        if (!iataCode.isEmpty()) {
           if (findAirportByIataCode(iataCode.trim())) return;
        }

        if (!localCode.isEmpty()) {
            if (findAirportByByLocalCode(localCode.trim())) return;
        }

        Alert a = new Alert(Alert.AlertType.INFORMATION, "No airport was found, please enter a valid ID, IATA or local code.");
        a.showAndWait();
    }

    private boolean findAirportByID(String ident) {
        for (Airport airport : airportList) {
            if (airport.getIdent().toLowerCase().equals(ident.toLowerCase())) {
                Airport foundAirport = airport;
                updateFields(foundAirport);
                return true;
            }
        }
        return false;
    }

    private boolean findAirportByIataCode(String iataCode) {
        for (Airport airport : airportList) {
            if (airport.getIataCode().toLowerCase().equals(iataCode.toLowerCase())) {
                Airport foundAirport = airport;
                updateFields(foundAirport);
                return true;
            }
        }
        return false;
    }

    private boolean findAirportByByLocalCode(String localCode) {
        for (Airport airport : airportList) {
            if (airport.getLocalCode().toLowerCase().equals(localCode.toLowerCase())) {
                Airport foundAirport = airport;
                updateFields(foundAirport);
                return true;
            }
        }
        return false;
    }

    private void updateFields(Airport airport) {
        identTextField.setText(airport.getIdent());
        iataCodeTextField.setText(airport.getIataCode());
        localCodeTextField.setText(airport.getLocalCode());
        typeTextField.setText(airport.getType());
        nameTextField.setText(airport.getName());
        elevationTextField.setText(String.valueOf(airport.getElevationFt()));
        countryTextField.setText(airport.getIsoCountry());
        regionTextField.setText(airport.getIsoRegion());
        municipalityTextField.setText(airport.getMunicipality());

        updateWeatherView(airport.getCoordinates().getLatitude(), airport.getCoordinates().getLongitude());
    }

    private void updateWeatherView(double latitude, double longitude) {
        WebEngine webEngine = webView.getEngine();
        String url = "https://www.windy.com/?" + latitude + "," + longitude + ",12";
        webEngine.load(url);
    }

    @FXML
    public void localCodeOnChange() {
        localCodeTextField.getText();
    }
    @FXML
    public void iataOnChange() {
        iataCodeTextField.getText();
    }
    @FXML
    public void identOnChange() {
        identTextField.getText();
    }
    @FXML
    public void onEnterPressedID() { searchAirport(); }
    @FXML
    public void onEnterPressedIata() { searchAirport(); }
    @FXML
    public void onEnterPressedLocal() { searchAirport(); }
    @FXML
    private void searchButtonAction() { searchAirport(); }
}