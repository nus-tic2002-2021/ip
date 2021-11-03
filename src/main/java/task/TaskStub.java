package task;

/**
 * Used for testing purposes.
 */
public class TaskStub extends Task {
    public TaskStub(String description) {
        super(description);
        super.isDone = true;
    }

    public String getStatusIcon() {
        return (isDone ? "+" : "-"); //+ if done, else -
    }

    public void markAsDone() {
        this.isDone = true;
    }

    public String toString() {
        return taskDesc;
    }
}