package com.alexooi.duke.tasks;

import com.alexooi.duke.enums.TaskType;

public class Event extends Task {
    private String timing;

    public Event(String description, String timing) {
        super(TaskType.EVENT, description);
        this.setTiming(timing);
    }

    public String getTiming() {
        return timing;
    }

    public void setTiming(String timing) {
        this.timing = timing;
    }

    @Override
    public String toStatusString() {
        return "[E]" + super.toStatusString() + " (at: " + timing + ")";
    }

    @Override
    public String toSaveString() {
        return super.toSaveString() + "|" + timing;
    }
}
