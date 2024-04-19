package au.edu.cpsc.module7;

import java.io.Serializable;

public class Note implements Serializable {
    private String title;
    private String content;
    private boolean isArchived;
    private String noteStyle;

    public Note(String title, String content, String noteStyle) {
        this.title = title;
        this.content = content;
        this.noteStyle = noteStyle;
        this.isArchived = false;
    }

    public String getContent() {
        return content;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getTitle() {
        return title;
    }

    public void setNoteStyle(String styleIn) {
        this.noteStyle = styleIn;
    }

    public String getNoteStyle() {
        return noteStyle;
    }

    public void toggleArchived() {
        if (this.isArchived == false) {
            this.isArchived = true;
        } else {
            this.isArchived = false;
        }
    }

    public boolean isArchived() {
        return isArchived;
    }

    @Override
    public String toString() {
        return "Note{" +
                "title='" + title + '\'' +
                ", content='" + content + '\'' +
                ", isArchived=" + isArchived +
                ", noteStyle='" + noteStyle + '\'' +
                '}';
    }
}
