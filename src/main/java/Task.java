package src.main.java;

public abstract class Task {
    protected String taskDetail;
    protected boolean isDone;
    protected TaskType taskType;

    public Task(String taskDetail) {
        this.taskDetail = taskDetail;
    }

    // Getter <<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<

    public String getTaskDetail() {
        return taskDetail;
    }

    public boolean getDoneStatus() {
        return isDone;
    }

    public TaskType getTaskType() {
        return taskType;
    }

    // Setter <<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<

    public void setTask(String modifiedTaskDetail) {
        taskDetail = modifiedTaskDetail;
    }

    public void setTaskCompleted() {
        isDone = true;
    }

    public void setTypeOfTask(TaskType taskType) {
        this.taskType = taskType;
    }

}
