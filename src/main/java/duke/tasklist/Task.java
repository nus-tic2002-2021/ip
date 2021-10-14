package duke.tasklist;

public class Task {

    protected String description;
    protected boolean isDone;
    protected static int totalCount = 0;

    public Task(String description) {
        this.description = description;
        this.isDone = false;
        totalCount++;
    }

    public String getTaskType() {
        return " ";
    }

    public String getDescription() {
        return description;
    }

    public String getDoneStatus() {
        return (isDone ? "X" : " ");
    }

    public String getTotalCount() {
        if (totalCount <= 1) return totalCount + " task";
        else return totalCount + " tasks";
    }

    public String getBy() { return "";}

    public String getAt() { return "";}

    public void setDone() {
        isDone = true;
    }

    @Override
    public String toString() {
        return String.format("[%s][%s] %s", getTaskType(), getDoneStatus(), description);
    }

}
