package classes;

import interfaces.Printable;

public class Task implements Printable {
    private String description;
    private Boolean isDone;

    public Task() {
        isDone = false;
    }

    public Task(String description) {
        this();
        setDescription(description);
    }

    public String getDescription() {
        return this.description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Boolean getDone() {
        return isDone;
    }

    public void setDone(Boolean done) {
        isDone = done;
    }

    @Override
    public String toStatusString() {
        String doneStr = (isDone) ? "X" : " ";
        return "[" + doneStr + "] " + toString();
    }

    @Override
    public String toString() {
        return getDescription();
    }
}
