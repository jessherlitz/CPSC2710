module edu.au.cpsc.miscstyle.module5 {
    requires javafx.controls;
    requires javafx.fxml;
            
                            
    opens edu.au.cpsc.miscstyle to javafx.fxml;
    exports edu.au.cpsc.miscstyle;

}