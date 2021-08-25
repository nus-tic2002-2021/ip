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

    //TODO: Add your code here
    @Override
    public String toString()
    {
        return id + ". [" + getStatusIcon() + "] " + description;
    }
}
