package au.edu.cpsc.module7;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;

public class NoteWindowController {

    @FXML
    private TextArea windowContent;


    @FXML
    public void initialize() {

    }
    public void setContent(String content) {
        windowContent = new TextArea();

        windowContent.setText(content);

    }
}
