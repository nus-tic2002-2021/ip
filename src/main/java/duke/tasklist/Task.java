package duke.tasklist;

/**
 * A <code>Task</code> object to store task information.
 */
public class Task {

    protected String description;
    protected boolean isDone;
    protected static int totalCount = 0;

    /**
     * Constructs Task with this description.
     *
     * @param description The task description.
     */
    public Task(String description) {
        this.description = description;
        this.isDone = false;
        totalCount++;
    }

    /**
     * Returns the task type.
     *
     * @return The task type.
     */
    public String getTaskType() {
        return " ";
    }

    /**
     * Returns the task description.
     *
     * @return The task description.
     */
    public String getDescription() {
        return description;
    }

    /**
     * Returns the task status.
     *
     * @return The task status.
     */
    public String getDoneStatus() {
        return (isDone ? "X" : " ");
    }

    /**
     * Returns the task deadline.
     *
     * @return The task deadline.
     */
    public String getBy() { return "";}

    /**
     * Returns the task datetime.
     *
     * @return The task datetime.
     */
    public String getAt() { return "";}

    /**
     * Mark the task as done.
     */
    public void setDone() {
        isDone = true;
    }

    @Override
    public String toString() {
        return String.format("[%s][%s] %s", getTaskType(), getDoneStatus(), description);
    }

}
