public class Task {
    protected String description;
    protected boolean isDone;


    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    public String getStatusIcon() {
        return (isDone ? "X" : " "); // mark done task with X
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

}