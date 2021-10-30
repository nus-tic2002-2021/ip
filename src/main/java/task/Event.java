package task;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Event extends Task {
    protected LocalDate eventTime;

    private final String DATE_FORMAT = "MMM d yyyy";
    public Event(String description, LocalDate eventTime) {
        super(description);
        setEvent(eventTime);
    }

    public Event(String description, LocalDate eventTime, Boolean isDone) {
        super(description);
        setEvent(eventTime);
        this.isDone = isDone;
    }

    public void setDone(){
        this.isDone = true;
        System.out.println("\tNice! I've marked this task as done:\n" +
                "\t  [E][X] " + getDescription() + "(at: " + getEventTimeFormat() + ")");
    }
    public void print(){
        if (isDone) {
            System.out.println("\t  [E][X] " + getDescription() + "(at: " + getEventTimeFormat() + ")");
        }
        else {
            System.out.println("\t  [E][ ] " + getDescription() + "(at: " + getEventTimeFormat() + ")");
        }

    }
    public void setEvent(LocalDate eventTime)
    {
        this.eventTime = eventTime;
    }

    public String getTask(){
        return "E";
    }
    public String getDescription(){
        return description;
    }
    public String getEventTimeFormat(){
        return eventTime.format(DateTimeFormatter.ofPattern(DATE_FORMAT));
    }
    public LocalDate getEventTime() {
        return eventTime;
    }
    public String getSave(){
        String s = getTask() + " | " +  getDone() + " | " + getDescription() + " | " + getEventTime();
        return s;
    }
    @Override
    public String toString() {
        String box = "[E][ ] ";
        if (isDone){
            box = "[E][X] ";
        }
        return (box + getDescription() + "(at: " + getEventTimeFormat() + ")");
    }
}
