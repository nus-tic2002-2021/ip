package Duke.Models;

/**
 * Represents a task that are to be done
 * with the description of the task.
 */
public class Todo extends Task {

    /**
     * Constructor method for To do object.
     * @param newTask
     */
    public Todo(String newTask) {
        super(newTask);
    }

    /**
     * Gets a string with task type symbol
     * and a cross symbol to indicate whether a task is complete.
     * @return String with task type and completed symbol.
     */
    public String getCompletedSymbol() {
        if (Completed) {
            return "[T][X]";
        } else {
            return "[T][ ]";
        }
    }

    /**
     * Returns task type symbol.
     * @return String with the initials of task type.
     */
    @Override
    public String getTaskType() {
        return "T";
    }
}
