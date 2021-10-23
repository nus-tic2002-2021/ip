package com.alexooi.duke.tasks;

import com.alexooi.duke.enums.TaskType;
import com.alexooi.duke.interfaces.DateParser;

import java.time.LocalDateTime;

public class Deadline extends Task {
    private String dueDateStr;
    private LocalDateTime dueDate;
    private DateParser dateParser;

    public Deadline(String description, String dueDate) {
        super(TaskType.DEADLINE, description);
        this.setDueDate(dueDate);
    }

    public Deadline(String description, String dueDate, DateParser parser) {
        super(TaskType.DEADLINE, description);
        this.dateParser = parser;
        this.setDueDate(dueDate);
    }

    public LocalDateTime getDueDate() {
        return dueDate;
    }

    public void setDueDate(String dueDate) {
        LocalDateTime dateTime = dateParser.parseInput(dueDate);
        this.dueDateStr = dueDate;
        this.dueDate = dateTime;
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
