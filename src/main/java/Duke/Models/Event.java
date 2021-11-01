package Duke.Models;

import java.time.LocalDate;
import java.time.LocalTime;

public class Event extends Task {
    protected String at;
    protected LocalDate date;
    protected LocalTime time;


    public Event(String newTask, String datetime) {
        super(newTask);
        at = datetime;
    }

    public Event(String newTask, LocalDate newDate) {
        super(newTask);
        date = newDate;
    }

    public Event(String newTask, LocalDate newDate, LocalTime newTime) {
        super(newTask);
        date = newDate;
        time = newTime;
    }

    public String getAdditionalInfo() {
        return at;
    }

    public String getCompletedSymbol() {
        if (Completed) {
            return "[E][X]";
        } else {
            return "[E][ ]";
        }
    }

    public String getTaskInfo() {
        return super.getTaskInfo() + " (at: " + at + ")";
    }

    @Override
    public String getTaskType() {
        return "E";
    }
}
