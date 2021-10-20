package Duke.task;

public class Task {
    protected String description;
    protected boolean isDone;

    public static final String DONE = "1";
    public static final String UNDONE = "0";

    /**
     * Task base constructor
     * @param description - the description string.
     */
    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    /**
     * Task's Current status indicator
     *
     * @return - done or not done
     */
    public String getStatusIcon() {
        return (isDone ? "X" : " "); // mark done task with X
    }

    /**
     * Similar to get status icon Status Code for code usage
     * @return - done or not done
     */
    public String getStatusCode() {
        return (isDone ? DONE : UNDONE); // mark done task with 1
    }

    /**
     * Gets the full description of each task
     * @return - returns task description
     */
    public String getTaskInfo(){
        return description;
    }

    /**
     * Sets the task to Done
     */
    public void setDone(){
        isDone = true;
    }

    /**
     * Base Function to print task
     * @return tasks [] TASK
     */
    public String toString() {
        return "["+getStatusIcon()+"] " + description;
    }

    /**
     * Encodes task for storage.
     * @return | X | TASK
     */
    public String encodeTask() {
        return " | " + getStatusCode() + " | " + description;
    }

}