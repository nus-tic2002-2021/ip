public class Task{
    private String taskDetail;
    private boolean done;

    public Task(String taskDetail) {
        this.taskDetail = taskDetail;
        this.done = false;
    }

    public void setTask(String modifiedTaskDetail) {
        taskDetail = modifiedTaskDetail;
    }

    public void setTaskCompleted() {
        done = true;
    }

    public String getTaskDetail() {
        return taskDetail;
    }

    public boolean getDoneStatus() {
        return done;
    }

}
