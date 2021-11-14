package task;

import java.util.Objects;

public class Deadline extends Task {

    private String by;

    public Deadline(String taskName, String by) {
        super(taskName);
        this.by = by;
    }

    public String getBy() {
        return by;
    }

    public void setBy(String by) {
        this.by = by;
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + by + ")";
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Deadline)) return false;
        if (!super.equals(o)) return false;
        Deadline deadline = (Deadline) o;
        return getBy().equals(deadline.getBy());
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), getBy());
    }
}
