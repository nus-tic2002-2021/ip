package dukeMain.tasks;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Event extends Task {

    protected LocalDate byDate;
    protected String byTime;

    public Event(String description, LocalDate byDate , String byTime) {
        super(description);
        this.byDate = byDate;
        this.byTime = byTime;
    }

    public Event(boolean done,String description, LocalDate byDate , String byTime) {
        super(description,done);
        this.byDate = byDate;
        this.byTime = byTime;
    }

    @Override
    public String toString() {
        String time = byTime.equals("") ? byTime : " " + byTime;
        return "[E]" + super.toString() + " (at: " + this.byDate.format(DateTimeFormatter.ofPattern("MMM d yyyy"))+time + ")";
    }

    @Override
    public String toStringSaveTask(String separator ){
        separator = " " + separator + " ";
        return "E" + separator + super.toStringSaveTask(separator) +  separator +this.byDate + separator + byTime;
    }
}