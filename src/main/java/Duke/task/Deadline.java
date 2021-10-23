package Duke.task;

import java.time.LocalDateTime;

public class Deadline extends Task {

    protected LocalDateTime by;

    /**
     * Deadline task constructor
     *
     * @param description - the task for the deadline
     * @param by - the deadline's datetime
     */
    public Deadline(String description, LocalDateTime by) {
        super(description);
        this.by = by;
    }

    /**
     * Prints task by its own style
     */
    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + by.toLocalDate().toString() + "["+ by.toLocalDate().getDayOfWeek() +"] "+ by.toLocalTime().toString()+")";
    }

    /**
     * Encode function for storage within text file
     *
     */
    @Override
    public String encodeTask() {
        return "D" + super.encodeTask() + " | " + by.toLocalDate().toString() + " | " + by.toLocalTime().toString();
    }
}