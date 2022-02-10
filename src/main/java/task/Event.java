package task;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.LocalTime;

public class Event extends Task {
    protected LocalDateTime eventTime;
    protected LocalTime to;

    private final String PRINT_FORMAT = "MMM d yyyy HH:mm a";
    private final String DATE_FORMAT = "yyyy-MM-dd";
    private final String SAVE_FORMAT = "yyyy-MM-dd HHmm";

    /**
     * Constructor for a new event created by user command
     * sets the description, eventTime and to of the task to be
     * description, eventTime and to argument respectively
     * and isDone is always false for newly added task.
     *
     * @param description description of the task
     * @param eventTime   start time of event
     * @param to          how long the event will be
     */
    public Event(String description, LocalDateTime eventTime, LocalTime to) {
        super(description);
        setEventTime(eventTime);
        setTo(to);
        setType();
    }

    /**
     * Constructor for a new task created by loading file
     * sets the description, eventTime and to of the task to be
     * description, eventTime and to argument respectively
     * isDone argument determines whether task isDone is true or false.
     *
     * @param description description of the task
     * @param eventTime   start time of event
     * @param to          how long the event will be
     * @param isDone      is program set as done
     */
    public Event(String description, LocalDateTime eventTime, LocalTime to, Boolean isDone) {
        super(description);
        setEventTime(eventTime);
        setTo(to);
        setType();
        this.isDone = isDone;
    }

    public void setDone() {
        this.isDone = true;
        System.out.println("Nice! I've marked this task as done:\n" +
                "  [E][X] " + getDescription() + "(at: " + getEventTimeFormat() + ")");
    }

    public void print() {
        if (isDone) {
            System.out.println("  [E][X] " + getDescription() + "(at: " + getEventTimeFormat() + ")");
        } else {
            System.out.println("  [E][ ] " + getDescription() + "(at: " + getEventTimeFormat() + ")");
        }

    }

    public void setEventTime(LocalDateTime eventTime) {
        this.eventTime = eventTime;
    }

    public void setTo(LocalTime to) {
        this.to = to;
    }

    public void setType() {
        type = Action.EVENT;
    }

    public String getTask() {
        return "E";
    }

    public String getDescription() {
        return description;
    }

    public String getEventTimeFormat() {
        return eventTime.format(DateTimeFormatter.ofPattern(PRINT_FORMAT));
    }

    public LocalDateTime getDateTime() {
        return eventTime;
    }

    public LocalTime getTime() {
        return to;
    }

    public String getHours() {
        String hours = to.getHour() + " Hours ";
        return hours;
    }

    public String getMinutes() {
        String minutes = to.getMinute() + " Minutes ";
        return minutes;
    }

    /**
     * Returns the format of the task to be saved.
     * gets task, isDone and description separated by ' | '
     *
     * @return the file format
     */
    public String getSave() {
        String s = getTask() + " | " + getDone() + " | " + getDescription() + " | " +
                getDateTime().format(DateTimeFormatter.ofPattern(SAVE_FORMAT)) + " | " + getTime();
        return s;
    }

    @Override
    public String toString() {
        String box = "[E][ ] ";
        if (isDone) {
            box = "[E][X] ";
        }
        return (box + getDescription() + "(at: " + getEventTimeFormat() + " for " + getHours() + getMinutes() + ")");
    }
}
