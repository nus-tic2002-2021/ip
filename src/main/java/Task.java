public class Task{
    private String task;
    private boolean done;

    public Task(String task) {
        this.task = task;
        this.done = false;
    }

    public void setTask(String modifiedTaskDetail) {
        task = modifiedTaskDetail;
    }

    public void setTaskCompleted() {
        done = true;
    }

    public String getTask() {
        return task;
    }

    public boolean getDoneStatus() {
        return done;
    }

}
