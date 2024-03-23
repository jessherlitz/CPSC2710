package edu.au.cpsc.airportapplication;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
    Map<String, Airport> airportCodeToAirportMap = new HashMap<>();

    public AirportApplicationController() throws IOException {
        try {
            airportList = Airport.readAll();
            createMap();
        } catch (IOException e) {
            System.out.println("There was an error while reading the file. " + e.getStackTrace());
        }
    }

    private String sanitizeInput(String in) {
        return in.toLowerCase().trim();
    }

    private void createMap() {
        for (Airport airport : airportList) {
            airportCodeToAirportMap.put(sanitizeInput(airport.getIataCode()), airport);
            airportCodeToAirportMap.put(sanitizeInput(airport.getLocalCode()), airport);
            airportCodeToAirportMap.put(sanitizeInput(airport.getIdent()), airport);
        }
    }
    private void searchAirport() {
        String ident = identTextField.getText();
        String iataCode = iataCodeTextField.getText();
        String localCode = localCodeTextField.getText();

        if (!ident.isEmpty()) {
            if (fetchAirport(ident.trim())) return;
        }

        if (!iataCode.isEmpty()) {
            if (fetchAirport(iataCode.trim())) return;
        }

        if (!localCode.isEmpty()) {
            if (fetchAirport(localCode.trim())) return;
        }

        Alert a = new Alert(Alert.AlertType.INFORMATION, "Please enter a valid ID, IATA or local code.");
        a.setHeaderText("No airport was found.");
        a.setTitle("Warning");
        a.showAndWait();
    }

    private boolean fetchAirport(String key) {
        if (!airportCodeToAirportMap.containsKey(sanitizeInput(key))) {
            return false;
        }

        updateFields(airportCodeToAirportMap.get(sanitizeInput(key)));
        return true;
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