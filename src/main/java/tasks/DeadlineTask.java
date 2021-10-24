package tasks;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;

/**
 * A <code>DeadLineTask</code> object stores a task with deadline.
 * * Extends the <code>Task</code> class.
 */
public class DeadlineTask extends Task {

    protected LocalDateTime deadline;

    /**
     * Constructor of <code>DeadLineTask</code>.
     *
     * @param description Brief description of the task.
     * @param deadline Deadline of the task.
     */
    public DeadlineTask(String description, LocalDateTime deadline) {
        super(description);
        this.deadline = deadline;
    }

    /**
     * Returns the deadline of the <code>DeadLineTask</code> object.
     */
    public String getBy() {
        return deadline.format(DateTimeFormatter.ofLocalizedDateTime(FormatStyle.MEDIUM, FormatStyle.SHORT));
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + getBy() + ")";
    }
}
