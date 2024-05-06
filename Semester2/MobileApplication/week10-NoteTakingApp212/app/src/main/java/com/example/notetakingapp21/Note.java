package com.example.notetakingapp21;

public class Note {

    private String title;
    private String description;
    private boolean important;

    public Note(String title, String description, boolean important) {
        this.title = title;
        this.description = description;
        this.important = important;
    }

    public Note(){}

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public boolean isImportant() {
        return important;
    }

    public void setImportant(boolean important) {
        this.important = important;
    }
}
