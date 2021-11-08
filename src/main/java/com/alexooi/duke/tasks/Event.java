package com.alexooi.duke.tasks;

import com.alexooi.duke.enums.TaskType;

import java.time.LocalDateTime;

public class Event extends Task {
    private LocalDateTime timing;

    public Event(String description, LocalDateTime timing) {
        super(TaskType.EVENT, description);
        this.setTiming(timing);
    }

    public LocalDateTime getTiming() {
        return timing;
    }

    public void setTiming(LocalDateTime timing) {
        this.timing = timing;
    }

    @Override
    public String toStatusString() {
        return "[E]" + super.toStatusString() + " (at: " + getTiming() + ")";
    }

    @Override
    public String toSaveString() {
        return super.toSaveString() + "|" + getTiming();
    }
}
