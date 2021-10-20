package tasks;

/**
 * A <code>ToDoTask</code> object stores a task without any specific date and time.
 * Extends the <code>Task</code> class.
 */
public class ToDoTask extends Task {

    /**
     * Constructor of <code>ToDoTask</code>.
     *
     * @param description Brief description of the task.
     */
    public ToDoTask(String description) {
        super(description);
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
