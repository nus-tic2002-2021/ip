package duke.tasklist;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * An <code>Event</code> object to store task information and event task description and datetime.
 * Extends the <code>Task</code> class.
 */
public class Event extends Task {

    protected String at;

    /**
     * Constructs Event task with this description and datetime.
     *
     * @param description The task description.
     * @param at The task datetime.
     */
    public Event(String description, String at) {
        super(description);

        DateTimeFormatter oldFormat = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        DateTimeFormatter newFormat = DateTimeFormatter.ofPattern("MMM dd yyyy hh:mm a");
        LocalDateTime dt = LocalDateTime.parse(at, oldFormat);
        this.at = dt.format(newFormat);
    }

    @Override
    public String getAt() {
        return at;
    }

    @Override
    public String getTaskType() {
        return "E";
    }

    @Override
    public String toString() {
        return String.format("%s (at: %s)", super.toString(), at);
    }

}
