module edu.au.cpsc.partii.module6partii {
    requires javafx.controls;
    requires javafx.fxml;
            
                            
    opens edu.au.cpsc.partii.module6partii to javafx.fxml;
    exports edu.au.cpsc.partii.module6partii;

    exports edu.au.cpsc.partii.module6partii.controller;
    exports edu.au.cpsc.partii.module6partii.model;
    opens edu.au.cpsc.partii.module6partii.model to javafx.fxml;

    opens edu.au.cpsc.partii.module6partii.controller to javafx.fxml;
    exports edu.au.cpsc.partii.module6partii.data;
    opens edu.au.cpsc.partii.module6partii.data to javafx.fxml;
}