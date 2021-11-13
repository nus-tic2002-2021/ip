package dukeMain.tasks;

public class Deadline extends Task {

    protected String by;

    public Deadline(String description, String by) {
        super(description);
        this.by = by;
    }
    public Deadline(boolean done,String description, String by) {
        super(description,done);
        this.by = by;
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + this.by + ")";
    }

    @Override
    public String toStringSaveTask(String separator ){
        separator = " " + separator + " ";
        return "D" + separator + super.toStringSaveTask(separator) + separator +this.by;
    }
}