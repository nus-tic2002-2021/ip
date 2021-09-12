import java.util.Objects;

public class Event extends Task {

    protected String at;

    public Event(String taskName, String at) {
        super(taskName);
        this.at = at;
    }

    public String getAt() {
        return at;
    }

    public void setAt(String at) {
        this.at = at;
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
        return Objects.equals(getAt(), event.getAt());
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), getAt());
    }
}
