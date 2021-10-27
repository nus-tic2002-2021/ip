package duke.tasklist;

/**
 * A <code>Task</code> object to store task information.
 */
public class Task {

    protected boolean isDone;
    protected String description;
    protected String tagDescription;

    /**
     * Constructs Task with this description.
     *
     * @param description The task description.
     */
    public Task(String description) {
        this.isDone = false;
        this.description = description;
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
     * Returns the task's tag description.
     *
     * @return The task's tag description.
     */
    public String getTagDescription() {
        return tagDescription;
    }

    /**
     * Marks the task as done.
     */
    public void setDone() {
        isDone = true;
    }

    /**
     * Tags the task with this description.
     */
    public String setTag(String tagDesc) {
        return tagDescription = tagDesc;
    }

    @Override
    public String toString() {
        return String.format("[%s][%s] %s #%s", getTaskType(), getDoneStatus(), description, tagDescription);
    }

}
