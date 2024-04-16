module au.edu.cpsc.module7 {
    requires javafx.controls;
    requires javafx.fxml;


    opens au.edu.cpsc.module7 to javafx.fxml;
    exports au.edu.cpsc.module7;
}
