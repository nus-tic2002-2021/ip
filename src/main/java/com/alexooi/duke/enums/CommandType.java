package com.alexooi.duke.enums;

public enum CommandType {
    ADD("todo <description>\n    deadline <description> /by <date>\n    event <description> /at <date>", "<date> must be in yyyy-mm-dd or yyyy-mm-ddThh:mm format."),
    REMOVE("remove <num>", "<num> must be a number that currently exists in the list."),
    COMPLETE("done <num>", "<num> must be a number that currently exists in the list."),
    ARCHIVE("archive <num>/all", "<num> must be a number that currently exists in the list."),
    VIEW_SCHEDULE("view <date>", "<date> must be in yyyy-mm-dd."),
    LIST("list"),
    FIND("find <description>"),
    EXIT("bye"),
    HELP("help");

    private String format;
    private String notes;

    CommandType(String format, String notes) {
        setFormat(format);
        setNotes(notes);
    }

    CommandType(String format) {
        setFormat(format);
    }

    public String getFormat() {
        return format;
    }

    public void setFormat(String format) {
        this.format = format;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }
}
