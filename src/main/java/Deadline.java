public class Deadline extends Task {

    protected String by;

    public Deadline(String description, String by) {
        super(description);
        this.by = by;
    }

    @Override
    public String toString() {
        return "Got it. I've added this task: \n  "+ super.toString() + " (by: " + by + ")";
    }
}