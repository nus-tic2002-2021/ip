public class Task {
    private String taskDetail;
    private boolean isDone;

    public Task(String taskDetail) {
        this.taskDetail = taskDetail;
        this.isDone = false;
    }

    // Getter <<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<

    public String getTaskDetail() {
        return taskDetail;
    }

    public boolean getDoneStatus() {
        return isDone;
    }

    // Setter <<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<

    public void setTask(String modifiedTaskDetail) {
        taskDetail = modifiedTaskDetail;
    }

    public void setTaskCompleted() {
        isDone = true;
    }

}
