package au.edu.cpsc.module7;

import javafx.collections.FXCollections;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.paint.Color;
import java.io.IOException;
import java.util.HashMap;

public class MainController {

    @FXML
    private ListView<Note> notesList;

    @FXML
    private TextField noteTitle;

    @FXML
    private TextArea noteContent;

    @FXML
    private BorderPane rootPane;

    @FXML
    private CheckBox archiveCheck;

    @FXML
    private ToggleButton archiveButton;

    @FXML
    private ColorPicker colorPicker;

    @FXML
    private Slider fontSizeSlider;

    private Database database = Db.getDatabase();

    private HashMap<String, String> map = new HashMap<String, String>();

    @FXML
    private void initialize() {
        rootPane.setFocusTraversable(true);
        notesList.setItems(FXCollections.observableList(database.getNotes()));
        notesList.getSelectionModel().selectedItemProperty().addListener(event -> noteSelectedChanged());
        notesList.setCellFactory(listView -> new NoteTitleCell());

        notesList.setOnMouseClicked(event -> {
            if (event.getClickCount() == 2) {
                Note selectedNote = notesList.getSelectionModel().getSelectedItem();
                try {
                    NoteWindowController.show(selectedNote.getTitle(), selectedNote.getContent());
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }});

        noteContent.setWrapText(true);
        fontSizeSlider.setMax(50);

        fontSizeSlider.valueProperty().addListener((obs, oldValue, newValue) -> {
            String fontStyle = newValue.intValue() + "px;";
            map.put("fontSize", fontStyle);
            addStyles();
        });
    }

    private void addStyles() {
        StringBuilder styles = new StringBuilder();

        if (map.get("fontSize") != null) {
            styles.append("-fx-font-size: " + map.get("fontSize"));
        }

        if (map.get("fontColor") != null) {
           styles.append("-fx-text-fill: " + map.get("fontColor"));
        }

        noteContent.setStyle(styles.toString());
    }


    @FXML
    private void colorPickerAction(Event event) {
        Color selectedColor = colorPicker.getValue();

        String hexColor = String.format("#%02X%02X%02X",
        (int) (selectedColor.getRed() * 255),
        (int) (selectedColor.getGreen() * 255),
        (int) (selectedColor.getBlue() * 255)) + ";";

        map.put("fontColor", hexColor);

        addStyles();
    }

    private void noteSelectedChanged() {
        Note selectedNote = notesList.getSelectionModel().getSelectedItem();

        if (selectedNote == null) {
            noteTitle.clear();
            noteContent.clear();
            archiveCheck.setSelected(false);
            noteContent.setStyle("");
            return;
        }

        noteTitle.setText(selectedNote.getTitle());
        noteContent.setText(selectedNote.getContent());
        archiveCheck.setSelected(selectedNote.isArchived());
        noteContent.setStyle(selectedNote.getNoteStyle());
    }


    @FXML
    private void saveButton() {
        String title = noteTitle.getText();
        String content = noteContent.getText();

        if (!title.isEmpty() && !content.isEmpty()) {
            Note note = new Note(title, content, noteContent.getStyle());

            database.addNote(note);
            Db.saveDatabase();
            noteTitle.clear();
            noteContent.clear();
        } else {
            showAlert("Notes cannot be empty.", "Please insert text to your note.", "Warning");
        }

        notesList.setItems(FXCollections.observableList(database.getNotes()));
    }


    @FXML
    private void deleteButton() {
        Note selectedNote = notesList.getSelectionModel().getSelectedItem();

        if (selectedNote == null) {
            showAlert("Cannot delete an empty note.", "Please select a note to be deleted.", "Warning");
        }

        database.removeNote(selectedNote);
        Db.saveDatabase();
        notesList.setItems(FXCollections.observableList(database.getNotes()));
    }

    @FXML
    private void editButton() {
        Note selectedNote = notesList.getSelectionModel().getSelectedItem();

        if (!noteContent.getText().isEmpty() && !noteTitle.getText().isEmpty()) {
            selectedNote.setContent(noteContent.getText());
            selectedNote.setTitle(noteTitle.getText());
            selectedNote.setNoteStyle(noteContent.getStyle());

            database.updateNote(selectedNote);

            Db.saveDatabase();
        } else {
            showAlert("Notes cannot be empty.","Please insert text to your note.", "Warning");
        }

        notesList.setItems(FXCollections.observableList(database.getNotes()));
    }

    @FXML
    private void handleDeleteKey(KeyEvent event) {
        if (event.getCode().toString().equals("BACK_SPACE")) {
            deleteButton();
        }
    }

    @FXML
    private void exitApp() {
        Note selectedNote = notesList.getSelectionModel().getSelectedItem();

        if (selectedNote != null && !selectedNote.getTitle().isEmpty() && !selectedNote.getContent().isEmpty()) {
            Db.saveDatabase();
            System.exit(0);
        } else {
            if (selectedNote.getTitle().isEmpty() || selectedNote.getContent().isEmpty()) {
                showAlert("Notes cannot be empty.","Please insert text to your note.", "Warning");
            }
        }
    }

    @FXML
    private void onAboutClick() {
        showAlert("Mini notes app for software class.", "Notes App.", "About");
    }

    @FXML
    private void archiveCheckBox() {

        Note selectedNote = notesList.getSelectionModel().getSelectedItem();

        if (selectedNote == null) {
            return;
        }

        selectedNote.toggleArchived();
        notesList.setItems(FXCollections.observableList(database.getNotes()));
        toggleArchived();
    }

    @FXML
    private void toggleArchived() {
        if (archiveButton.isSelected()) {
            archiveButton.setText("Hide Archived");
            notesList.setItems(FXCollections.observableList(database.getAllNotes()));
        } else {
            notesList.setItems(FXCollections.observableList(database.getNotes()));
            archiveButton.setText("Show Archived");
        }
    }
    
    private void showAlert(String info, String headerText, String title) {
        Alert a = new Alert(Alert.AlertType.INFORMATION, info);
        a.setHeaderText(headerText);
        a.setTitle(title);
        a.showAndWait();
    }

    static class NoteTitleCell extends ListCell<Note> {
        @Override
        protected void updateItem(Note item, boolean empty) {
            super.updateItem(item, empty);


            if (empty || item == null) {
                setText(null);
                setGraphic(null);
            } else {
                if (item.getTitle().length() >= 25) {
                    setText(item.getTitle().substring(0, 25) + "...");
                } else {
                    setText(item.getTitle());
                }
            }
        }
    }
}
