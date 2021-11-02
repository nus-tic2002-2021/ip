package Duke.Models;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

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
        String info;
        if (date == null) {
            info = at;
        } else {
            info = date.toString();
            if (time != null) {
                info = info + " " + time;
            }
        }
        return info;
    }

    public String getCompletedSymbol() {
        if (Completed) {
            return "[E][X]";
        } else {
            return "[E][ ]";
        }
    }

    public String getTaskInfo() {
        String info = super.getTaskInfo() + " (at: ";
        if (date == null) {
            info = info + at;
        } else {
            info = info + date.format(DateTimeFormatter.ofPattern("MMM dd yyyy"));
            if (time != null) {
                info = info + " " + time;
            }
        }
        info = info + ")";
        return info;
    }

    @Override
    public String getTaskType() {
        return "E";
    }

    public void printDate() {
        System.out.println(date.format(DateTimeFormatter.ofPattern("MMM dd yyyy")));
    }
}
