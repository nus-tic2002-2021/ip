package com.alexooi.duke.tasks;

import com.alexooi.duke.enums.TaskType;
import com.alexooi.duke.interfaces.DateParser;

import java.time.LocalDateTime;

public class Event extends Task {
    private String timingStr;
    private LocalDateTime timing;
    private DateParser dateParser;

    public Event(String description, String timing) {
        super(TaskType.EVENT, description);
        this.setTiming(timing);
    }

    public Event(String description, String timing, DateParser parser) {
        super(TaskType.EVENT, description);
        this.dateParser = parser;
        this.setTiming(timing);
    }

    public LocalDateTime getTiming() {
        return timing;
    }

    public void setTiming(String timing) {
        LocalDateTime dateTime = dateParser.parseInput(timing);
        this.timingStr = timing;
        this.timing = dateTime;
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
