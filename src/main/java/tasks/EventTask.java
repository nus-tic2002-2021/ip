package tasks;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;

/**
 * A <code>EventTask</code> object stores a task (event) with date time.
 * Extends the <code>Task</code> class.
 */
public class EventTask extends Task {

    protected LocalDateTime start;
    protected LocalDateTime end;

    /**
     * Constructor of <code>EventTask</code>.
     *
     * @param description Brief description of the task.
     * @param start Start DateTime of the task (event).
     * @param end End DateTime of the task (event).
     */
    public EventTask(String description, LocalDateTime start, LocalDateTime end) {
        super(description);
        this.start = start;
        this.end = end;
    }

    /**
     * Returns the event start date time of the <code>EventTask</code> object.
     */
    public String getStart() {
        return start.format(DateTimeFormatter.ofLocalizedDateTime(FormatStyle.MEDIUM, FormatStyle.SHORT));
    }

    /**
     * Returns the event start date time of the <code>EventTask</code> object.
     */
    public String getEnd() {
        return end.format(DateTimeFormatter.ofLocalizedDateTime(FormatStyle.MEDIUM, FormatStyle.SHORT));
    }

    /**
     * Returns the event date time (period) of the <code>EventTask</code> object.
     */
    public String getAt() {
        return getStart() + " - " + getEnd();
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (at: " + getAt() + ")";
    }
}
