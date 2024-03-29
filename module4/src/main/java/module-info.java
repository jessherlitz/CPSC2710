module edu.au.cpsc.module4 {
    requires javafx.controls;
    requires javafx.fxml;


    opens edu.au.cpsc.module4 to javafx.fxml;
    exports edu.au.cpsc.module4;
    exports edu.au.cpsc.module4.controller;
    exports edu.au.cpsc.module4.model;
    opens edu.au.cpsc.module4.model to javafx.fxml;

    opens edu.au.cpsc.module4.controller to javafx.fxml;
    exports edu.au.cpsc.module4.data;
    opens edu.au.cpsc.module4.data to javafx.fxml;
}