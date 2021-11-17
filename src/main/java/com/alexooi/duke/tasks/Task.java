package com.alexooi.duke.tasks;

import com.alexooi.duke.enums.TaskType;

public abstract class Task {
    private String description;
    private boolean isDone;
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

    public boolean getDone() {
        return isDone;
    }

    public void setDone(boolean isDone) {
        this.isDone = isDone;
    }

    public TaskType getType() {
        return type;
    }

    public void setType(TaskType type) {
        this.type = type;
    }

    public String toStatusString() {
        String doneStr = (getDone()) ? "X" : " ";
        return "[" + doneStr + "] " + this;
    }

    @Override
    public String toString() {
        return getDescription();
    }

    public String toSaveString() {
        String doneStr = (getDone()) ? "T" : "F";
        return getType() + "|" + doneStr + "|" + getDescription();
    }
}
