package duke.tasklist;

/**
 * A <code>Todo</code> object to store task information and todo task description.
 * Extends the <code>Task</code> class.
 */
public class Todo extends Task {

    /**
     * Constructs Todo task with this description.
     *
     * @param description The task description.
     */
    public Todo(String description) {
        super(description);
    }

    @Override
    public String getTaskType() {
        return "T";
    }

    @Override
    public String toString() {
        return String.format("%s", super.toString());
    }

}
