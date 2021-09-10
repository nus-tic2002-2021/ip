package classes.tasks;

import classes.enums.TaskType;

public abstract class Task {
    private String description;
    private Boolean isDone;
    private TaskType type;

    public Task() {
        isDone = false;
    }

    public Task(TaskType type, String description) {
        this();
        setType(type);
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

    public TaskType getType() {
        return type;
    }

    public void setType(TaskType type) {
        this.type = type;
    }

    public String toStatusString() {
        String doneStr = (isDone) ? "X" : " ";
        return "[" + doneStr + "] " + this;
    }

    @Override
    public String toString() {
        return getDescription();
    }
}
