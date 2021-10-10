package tasks;

/**
 * A <code>Task</code> is a high-level Task class to be extended.
 */
public class Task {
    private static int taskCount;

    protected int id;
    protected String description;
    protected boolean isDone;

    /**
     * Constructor of <code>Task</code>.
     *
     * @param description Brief description of the task.
     */
    public Task(String description)
    {
        taskCount++;

        this.id = taskCount;
        this.description = description;
        this.isDone = false;
    }

    /** Returns the description of the task. */
    public String getDescription() {
        return description;
    }

    /** Returns the status flag of the task. */
    public String getStatusIcon() { return (isDone ? "X" : " "); }

    /** Returns the status of the task. */
    public boolean isDone() { return isDone; }

    /** Set the status of the task to "done". */
    public void markAsDone(){
        isDone = true;
    }

    @Override
    public String toString()
    {
        return "[" + getStatusIcon() + "] " + getDescription();
    }
}
