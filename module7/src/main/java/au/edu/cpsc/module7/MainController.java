package au.edu.cpsc.module7;

import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.BorderPane;

public class MainController {

    @FXML
    private ListView<Note> notesList;

    @FXML
    private TextField noteTitle;

    @FXML
    private TextArea noteContent;

    @FXML
    private BorderPane rootPane;

    Database database = Db.getDatabase();

    @FXML
    private void initialize() {
        rootPane.setFocusTraversable(true);
        notesList.setItems(FXCollections.observableList(database.getNotes()));
        notesList.getSelectionModel().selectedItemProperty().addListener(event -> noteSelectedChanged());
        notesList.setCellFactory(listView -> new NoteTitleCell());

    }

    private void noteSelectedChanged() {
        Note selectedNote = notesList.getSelectionModel().getSelectedItem();

        if (selectedNote == null) {
            noteTitle.clear();
            noteContent.clear();
            return;
        }

        noteTitle.setText(selectedNote.getTitle());
        noteContent.setText(selectedNote.getContent());

    }


    @FXML
    public void saveButton() {
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
    public void deleteButton() {
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
    public void editButton() {
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
    public void handleDeleteKey(KeyEvent event) {
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
