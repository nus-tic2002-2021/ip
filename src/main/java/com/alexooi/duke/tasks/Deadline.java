package com.alexooi.duke.tasks;

import com.alexooi.duke.enums.TaskType;

import java.time.LocalDateTime;

public class Deadline extends Task {
    private LocalDateTime dueDate;

    public Deadline(String description, LocalDateTime dueDate) {
        super(TaskType.DEADLINE, description);
        this.setDueDate(dueDate);
    }

    public LocalDateTime getDueDate() {
        return dueDate;
    }

    public void setDueDate(LocalDateTime dueDate) {
        this.dueDate = dueDate;
    }

    @Override
    public String toStatusString() {
        return "[D]" + super.toStatusString() + " (by: " + getDueDate() + ")";
    }

    @Override
    public String toSaveString() {
        return super.toSaveString() + "|" + dueDate;
    }
}
