package duke.task;

import duke.app.Parser;

import java.time.LocalDateTime;
import java.util.Objects;

/**
 * Represent a type of task with a specific time, represented by a dateTime object.
 *
 */
public class Event extends Task {

    private LocalDateTime at;

    public Event(String taskName, String at) {
        super(taskName);
        this.at = Parser.parseDateTime(at);
    }

    public String getTaskType() { return "EVENT"; }

    @Override
    public String getTime() {
        return at.toString().replace("T", " ");
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() +
                " (at: " + Parser.formatDatetimeToString(at) + ") " + getTag();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Event)) return false;
        if (!super.equals(o)) return false;
        Event event = (Event) o;
        return Objects.equals(getTime(), event.getTime());
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), getTime());
    }
}
