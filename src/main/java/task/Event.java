package task;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Event extends Task {
    protected LocalDateTime eventTime;

    private final String PRINT_FORMAT = "MMM d yyyy HH:mm a";
    private final String SAVE_FORMAT = "yyyy-MM-dd HHmm";
    public Event(String description, LocalDateTime eventTime) {
        super(description);
        setEvent(eventTime);
    }

    public Event(String description, LocalDateTime eventTime, Boolean isDone) {
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
    public void setEvent(LocalDateTime eventTime)
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
        return eventTime.format(DateTimeFormatter.ofPattern(PRINT_FORMAT));
    }
    public LocalDateTime getEventTime() {
        return eventTime;
    }
    public String getSave(){
        String s = getTask() + " | " +  getDone() + " | " + getDescription() + " | " + getEventTime().format(DateTimeFormatter.ofPattern(SAVE_FORMAT));
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
