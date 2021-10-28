package duke.task;

public class Task {
    protected String description;
    protected boolean isDone;

    public static final String DONE = "1";
    public static final String UNDONE = "0";

    /**
     * Creates Task base.
     *
     * @param description - the description string.
     */
    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    /**
     * Returns task's current status.
     *
     * @return - done or not done.
     */
    public String getStatusIcon() {
        return (isDone ? "X" : " "); // mark done task with X
    }

    /**
     * Returns task's current status for code usage.
     *
     * @return - 0 or 1.
     */
    public String getStatusCode() {
        return (isDone ? DONE : UNDONE); // mark done task with 1
    }

    /**
     * Returns the full description of each task.
     *
     * @return - returns task description.
     */
    public String getTaskInfo(){
        return description;
    }

    /** Sets the task to Done. */
    public void setDone(){
        isDone = true;
    }

    /**
     * Base Function to print task.
     *
     * @return tasks [] TASK.
     */
    public String toString() {
        return "["+getStatusIcon()+"] " + description;
    }

    /**
     * Encodes task for storage.
     *
     * @return | X | TASK.
     */
    public String encodeTask() {
        return " | " + getStatusCode() + " | " + description;
    }

}