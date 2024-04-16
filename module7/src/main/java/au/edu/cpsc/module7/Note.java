package au.edu.cpsc.module7;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;


public class Note {
    private String title;
    private String content;

    public Note(String title, String content) {
        this.title = title;
        this.content = content;
    }

    public static void saveNotes(List<Note> notes, String filePath) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath))) {
            for (Note note : notes) {
                writer.write(note.getTitle() + "::" + note.getContent() + "\n");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private String getContent() {
        return content;
    }

    private String getTitle() {
        return title;
    }

    public static List<Note> loadNotes(String filePath) {
        List<Note> notes = new ArrayList<>();
        try {
            List<String> lines = Files.readAllLines(Paths.get(filePath));
            for (String line : lines) {
                String[] parts = line.split("::", 2);
                if (parts.length >= 2) {
                    Note note = new Note(parts[0], parts[1]);
                    notes.add(note);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return notes;
    }
}
