package Duke.task;

public class Deadline extends Task {

    protected String by;

    /**
     * Deadline task constructor
     *
     * @param description - the task for the deadline
     * @param by - the deadline's datetime
     */
    public Deadline(String description, String by) {
        super(description);
        this.by = by;
    }

    /**
     * Prints task by its own style
     */
    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + by + ")";
    }

    /**
     * Encode function for storage within text file
     *
     */
    @Override
    public String encodeTask() {
        return "D" + super.encodeTask() + "|" + by;
    }
}