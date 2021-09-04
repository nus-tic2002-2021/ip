package tasks;

public class Task {
    private static int taskCount;

    protected int id;
    protected String description;
    protected boolean isDone;

    public Task(String description)
    {
        taskCount++;

        this.id = taskCount;
        this.description = description;
        this.isDone = false;
    }

    public int getTaskId() { return id; }

    public String getDescription() {
        return description;
    }

    public String getStatusIcon()
    {
        return (isDone ? "X" : " ");
    }

    public void markAsDone(){
        isDone = true;
    }

    @Override
    public String toString()
    {
        return "[" + getStatusIcon() + "] " + getDescription();
    }
}
