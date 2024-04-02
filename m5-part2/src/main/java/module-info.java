module edu.au.cpsc.launcher.m5part2 {
    requires javafx.controls;
    requires javafx.fxml;


    opens edu.au.cpsc.launcher.m5part2 to javafx.fxml;
    exports edu.au.cpsc.launcher.m5part2;
}