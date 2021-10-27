package duke.tasklist;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * A <code>Deadline</code> object to store task information and deadline task description and deadline.
 * Extends the <code>Task</code> class.
 */
public class Deadline extends Task {

    protected String by;

    /**
     * Constructs Deadline task with this description and deadline.
     *
     * @param description The task description.
     * @param by The task deadline.
     */
    public Deadline(String description, String by) {
        super(description);

        DateTimeFormatter oldFormat = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        DateTimeFormatter newFormat = DateTimeFormatter.ofPattern("MMM dd yyyy hh:mm a");
        LocalDateTime dt = LocalDateTime.parse(by, oldFormat);
        this.by = dt.format(newFormat);
    }

    @Override
    public String getBy() {
        return by;
    }

    @Override
    public String getTaskType() {
        return "D";
    }

    @Override
    public String toString() {
        return String.format("[%s][%s] %s (by: %s) #%s", getTaskType(), getDoneStatus(), description, by, tagDescription);
    }

}
