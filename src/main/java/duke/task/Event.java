package duke.task;

import java.util.Objects;

public class Event extends Task {

    private String at;

    public Event(String taskName, String at) {
        super(taskName);
        this.at = at;
    }

    public String getTaskType() { return "EVENT"; }

    @Override
    public String getTime() {
        return at;
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (at: " + at + ")";
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
