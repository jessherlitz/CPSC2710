package au.edu.cpsc.module7;

import javafx.fxml.FXML;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import java.util.ArrayList;
import java.util.List;

public class MainController {
    @FXML
    private TextField noteTitle;

    @FXML
    private TextArea noteContent;

    private List<Note> notes = new ArrayList<>();
    private final String NOTES_FILE = "notes.txt";

    @FXML
    private void initialize() {
        notes = Note.loadNotes(NOTES_FILE);

    }

    @FXML
    private void newNote() {
        String title = noteTitle.getText();
        String content = noteContent.getText();

        Note note = new Note(title, content);
        notes.add(note);

        Note.saveNotes(notes, NOTES_FILE);
    }

    @FXML
    private void deleteNote() {

    }

    @FXML
    private void exitApp() {
        Note.saveNotes(notes, NOTES_FILE);
        System.exit(0);
    }
}
