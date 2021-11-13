package dukeMain.tasks;

public class Event extends Task {

    protected String by;

    public Event(String description, String by) {
        super(description);
        this.by = by;
    }
    public Event(boolean done, String description, String by) {
        super(description,done);
        this.by = by;
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (at: " + this.by + ")";
    }

    @Override
    public String toStringSaveTask(String separator ){
        separator = " " + separator + " ";
        return "E" + separator + super.toStringSaveTask(separator) + separator +this.by;
    }
}