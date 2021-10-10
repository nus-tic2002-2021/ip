package tasks;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;

/**
 * A <code>EventTask</code> object stores a task (event) with date time.
 * Extends the <code>Task</code> class.
 */
public class EventTask extends Task {

    protected LocalDateTime at;

    /**
     * Constructor of <code>EventTask</code>.
     *
     * @param description Brief description of the task.
     * @param at DateTime of the task (event).
     */
    public EventTask(String description, LocalDateTime at) {
        super(description);
        this.at = at;
    }

    /**
     * Returns the event date time of the <code>EventTask</code> object.
     */
    public String getAt() { return at.format(DateTimeFormatter.ofLocalizedDateTime(FormatStyle.MEDIUM)); }

    @Override
    public String toString()
    {
        return "[E]" + super.toString() + " (at: " + getAt() + ")";
    }
}
