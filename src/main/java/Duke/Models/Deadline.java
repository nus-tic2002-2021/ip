package Duke.Models;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

public class Deadline extends Task {
    protected String by;
    protected LocalDate date;
    protected LocalTime time;

    public Deadline(String newTask, String datetime) {
        super(newTask);
        by = datetime;
    }

    public Deadline(String newTask, LocalDate newDate) {
        super(newTask);
        date = newDate;
    }

    public Deadline(String newTask, LocalDate newDate, LocalTime newTime) {
        super(newTask);
        date = newDate;
        time = newTime;
    }

    public String getAdditionalInfo() {
        return by;
    }

    public String getCompletedSymbol() {
        if (Completed) {
            return "[D][X]";
        } else {
            return "[D][ ]";
        }
    }

    public String getTaskInfo() {
        String info = super.getTaskInfo() + " (by: ";
        if (date == null) {
            info = info + by;
        } else {
            info = info + date.format(DateTimeFormatter.ofPattern("MMM dd yyyy")).toString();
            if (time != null) {
                info = info + " " + time.toString();
            }
        }
        info = info + ")";
        return info;
    }

    @Override
    public String getTaskType() {
        return "D";
    }
}
