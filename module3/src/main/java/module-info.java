module edu.au.cpsc.airportapplication {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.web;

    opens edu.au.cpsc.airportapplication to javafx.fxml;
    exports edu.au.cpsc.airportapplication;
}