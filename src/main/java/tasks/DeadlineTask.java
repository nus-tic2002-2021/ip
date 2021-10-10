package tasks;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;

/**
 * A <code>DeadLineTask</code> object stores a task with deadline.
 * * Extends the <code>Task</code> class.
 */
public class DeadlineTask extends Task {

    protected LocalDate by;

    /**
     * Constructor of <code>DeadLineTask</code>.
     *
     * @param description Brief description of the task.
     * @param by Deadline of the task.
     */
    public DeadlineTask(String description, LocalDate by) {
        super(description);
        this.by = by;
    }

    /**
     * Returns the deadline of the <code>DeadLineTask</code> object.
     */
    public String getBy(){ return by.format(DateTimeFormatter.ofLocalizedDate(FormatStyle.MEDIUM)); }

    @Override
    public String toString(){ return "[D]" + super.toString() + " (by: " + getBy() + ")"; }
}
