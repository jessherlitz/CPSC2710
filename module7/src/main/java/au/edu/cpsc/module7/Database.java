package au.edu.cpsc.module7;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Database implements Serializable {

    private List<Note> notes;

    public Database() {
        notes = new ArrayList<>();
    }

    public List<Note> getNotes() {
        return notes;
    }

    public void addNote(Note note) {
        notes.add(note);
    }

    public void removeNote(Note note) {
        notes.remove(note);
    }

    public void updateNote(Note note) {
        int index = notes.indexOf(note);
        if (index != -1) {
            notes.set(index, note);
        }
    }
}