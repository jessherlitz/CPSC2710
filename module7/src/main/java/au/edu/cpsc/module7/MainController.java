package au.edu.cpsc.module7;

import javafx.collections.FXCollections;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.BorderPane;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import java.io.IOException;

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

    private NoteWindowController noteWindowController;

    Database database = Db.getDatabase();

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
                    noteWindow(selectedNote.getTitle(), selectedNote.getContent());
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }});

// it overwrites the color;
//        fontSizeSlider.valueProperty().addListener((obs, oldValue, newValue) -> {
//            System.out.println("event");
//            noteContent.setStyle("-fx-font-size: " + newValue.intValue() + "px;");
//        });
    }


    @FXML
    private void colorPickerAction(Event event) {
        Color selectedColor = colorPicker.getValue();

        String hexColor = String.format("#%02X%02X%02X",
        (int) (selectedColor.getRed() * 255),
        (int) (selectedColor.getGreen() * 255),
        (int) (selectedColor.getBlue() * 255));

        noteContent.setStyle("-fx-text-fill: " + hexColor + ";");
    }

    private void noteSelectedChanged() {
        Note selectedNote = notesList.getSelectionModel().getSelectedItem();

        if (selectedNote == null) {
            noteTitle.clear();
            noteContent.clear();
            archiveCheck.setSelected(false);
            return;
        }

        noteTitle.setText(selectedNote.getTitle());
        noteContent.setText(selectedNote.getContent());
        archiveCheck.setSelected(selectedNote.isArchived());
    }


    @FXML
    private void saveButton() {
        String title = noteTitle.getText();
        String content = noteContent.getText();

        if (!title.isEmpty() && !content.isEmpty()) {
            Note note = new Note(title, content);
            database.addNote(note);
            Db.saveDatabase();
            noteTitle.clear();
            noteContent.clear();
        } else {
            Alert a = new Alert(Alert.AlertType.INFORMATION, "Notes cannot be empty.");
            a.setHeaderText("Please insert text to your note.");
            a.setTitle("Warning");
            a.showAndWait();
        }

        notesList.setItems(FXCollections.observableList(database.getNotes()));
    }


    @FXML
    private void deleteButton() {
        Note selectedNote = notesList.getSelectionModel().getSelectedItem();

        if (selectedNote == null) {
            Alert a = new Alert(Alert.AlertType.INFORMATION, "Cannot delete an empty note.");
            a.setHeaderText("Please select a note to be deleted.");
            a.setTitle("Warning");
            a.showAndWait();
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

            database.updateNote(selectedNote);

            Db.saveDatabase();
        } else {
            Alert a = new Alert(Alert.AlertType.INFORMATION, "Notes cannot be empty.");
            a.setHeaderText("Please insert text to your note.");
            a.setTitle("Warning");
            a.showAndWait();
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
                Alert a = new Alert(Alert.AlertType.INFORMATION, "Notes cannot be empty.");
                a.setHeaderText("Please insert text to your note.");
                a.setTitle("Warning");
                a.showAndWait();
            }
        }
    }

    @FXML
    private void onAboutClick() {
        Alert a = new Alert(Alert.AlertType.INFORMATION, "Mini notes app for software class.");
        a.setHeaderText("Notes App.");
        a.setTitle("About");
        a.showAndWait();
    }

    @FXML
    private void archiveCheckBox() {

        Note selectedNote = notesList.getSelectionModel().getSelectedItem();

        if (selectedNote == null) {
            return;
        }

        selectedNote.toggleArchived();
        notesList.setItems(FXCollections.observableList(database.getNotes()));
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

    @FXML
    protected void noteWindow(String title, String content) throws IOException {
        noteWindowController = new NoteWindowController();

        Stage stage = new Stage();
        FXMLLoader fxmlLoader = new FXMLLoader(NotesApp.class.getResource("note-window.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        stage.setTitle(title);
        noteWindowController.initialize();
        noteWindowController.setContent(content);
        stage.setScene(scene);
        stage.show();
    }

    static class NoteTitleCell extends ListCell<Note> {
        @Override
        protected void updateItem(Note item, boolean empty) {
            super.updateItem(item, empty);


            if (empty || item == null) {
                setText(null);
                setGraphic(null);
            } else {
                setText(item.getTitle() );
            }
        }
    }
}
