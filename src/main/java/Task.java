
public class Task {

    protected String description;
    protected boolean isDone;
    protected int id = 0;
    protected static int totalCount = 0;

    public Task(String description) {
        this.description = description;
        this.isDone = false;
        id = totalCount + 1;
        totalCount++;
    }

    public String getDescription() {
        return description;
    }

    public String getDoneStatus() {
        return (isDone ? "X" : " ");
    }

    // ?
    public String getTaskType() {
        return " ";
    }

    public int getID() {
        return id;
    }

    // ?
    public String getTotalCount() {
        if (totalCount == 1) return totalCount + " task";
        else return totalCount + " tasks";
    }

    public void setDone() {
        isDone = true;
    }

    @Override
    public String toString() {
        return String.format("%s. [%s][%s] %s", id, getTaskType(), getDoneStatus(), description);
    }

}
