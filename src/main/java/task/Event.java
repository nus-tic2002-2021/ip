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
    public Event(String description, LocalDateTime eventTime, LocalTime to) {
        super(description);
        setEventTime(eventTime);
        setTo(to);
        setType();
    }

    public Event(String description, LocalDateTime eventTime, LocalTime to, Boolean isDone) {
        super(description);
        setEventTime(eventTime);
        setTo(to);
        setType();
        this.isDone = isDone;
    }

    public void setDone(){
        this.isDone = true;
        System.out.println("Nice! I've marked this task as done:\n" +
                "  [E][X] " + getDescription() + "(at: " + getEventTimeFormat() + ")");
    }
    public void print(){
        if (isDone) {
            System.out.println("  [E][X] " + getDescription() + "(at: " + getEventTimeFormat() + ")");
        } else {
            System.out.println("  [E][ ] " + getDescription() + "(at: " + getEventTimeFormat() + " for " + getHours() + getMinutes() + ")" );
        }

    }
    public void setEventTime(LocalDateTime eventTime){
        this.eventTime = eventTime;
    }
    public void setTo(LocalTime to){
        this.to = to;
    }
    public void setType(){
        type = "event";
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
    public LocalDateTime getDateTime(){
        return eventTime;
    }
    public LocalTime getTime(){
        return to;
    }
    public String getHours(){
        String hours = to.getHour() + " Hours ";
        return hours;
    }
    public String getMinutes(){
        String minutes = to.getMinute() + " Minutes ";
        return minutes;
    }
    public String getSave(){
        String s = getTask() + " | " +  getDone() + " | " + getDescription() + " | " +
                getDateTime().format(DateTimeFormatter.ofPattern(SAVE_FORMAT)) + " | " + getTime();
        return s;
    }

    @Override
    public String toString() {
        String box = "[E][ ] ";
        if (isDone){
            box = "[E][X] ";
        }
        return (box + getDescription() + "(at: " + getEventTimeFormat() + " for " + getHours() + getMinutes() + ")");
    }
}
