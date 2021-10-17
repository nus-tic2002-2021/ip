package Duke.task;

public class Task {
    protected String description;
    protected boolean isDone;

    public static final String DONE = "1";
    public static final String UNDONE = "0";

    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    public String getStatusIcon() {
        return (isDone ? "X" : " "); // mark done task with X
    }

    public String getStatusCode() {
        return (isDone ? DONE : UNDONE); // mark done task with 1
    }

    public String getTaskInfo(){
        return description;
    }

    public void setDone(){
        isDone = true;
    }

    public String toString() {
        return "["+getStatusIcon()+"] " + description;
    }

    public String encodeTask() {
        return " | " + getStatusCode() + " | " + description;
    }

}