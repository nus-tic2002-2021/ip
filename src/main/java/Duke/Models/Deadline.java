package Duke.Models;

import java.time.LocalDate;
import java.time.LocalTime;

public class Deadline extends Task {
    protected String by;
    protected LocalDate date;
    protected LocalTime time;

    public Deadline(String newTask, String datetime) {
        super(newTask);
        by = datetime;
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
        return super.getTaskInfo() + " (by: " + by + ")";
    }

    @Override
    public String getTaskType() {
        return "D";
    }
}
