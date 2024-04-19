package au.edu.cpsc.module7;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.IOException;

public class NoteWindowController {

    @FXML
    private TextArea windowContent;

    public static void show(String title, String content) throws IOException {
        Stage stage = new Stage(StageStyle.UTILITY);
        FXMLLoader fxmlLoader = new FXMLLoader(NotesApp.class.getResource("note-window.fxml"));
        Scene scene = new Scene(fxmlLoader.load());

        NoteWindowController controller = fxmlLoader.getController();

        controller.setContent(title, content);
        stage.setTitle(title);
        stage.setScene(scene);
        stage.show();
    }

    private void setContent(String title, String content) {
        windowContent.setEditable(false);
        windowContent.setText(content);
    }
}
