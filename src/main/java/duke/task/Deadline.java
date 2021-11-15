package duke.task;

import duke.app.Parser;

import java.time.LocalDateTime;
import java.util.Objects;


/**
 * Represent a task with a specific deadline, represented by a dateTime object.
 */
public class Deadline extends Task {

    private LocalDateTime by;

    public Deadline(String taskName, String by) {
        super(taskName);
        this.by = Parser.parseDateTime(by);
    }

    public String getTaskType() { return "DEADLINE"; }

    @Override
    public String getTime() {
        return by.toString().replace("T", " ");
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + Parser.formatDatetimeToString(by) + ")";
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Deadline)) return false;
        if (!super.equals(o)) return false;
        Deadline deadline = (Deadline) o;
        return getTime().equals(deadline.getTime());
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), getTime());
    }
}
