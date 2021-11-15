package duke.task;

import java.util.Objects;

public abstract class Task {

    private final String taskName;
    private boolean isDone;

    public Task(String taskName) {
        this.taskName = taskName;
        this.isDone = false;
    }

    public String getStatusIcon() {
        return (isDone ? "X" : " "); // mark done task with X
    }

    public String getTaskName() {
        return taskName;
    }

    public boolean isDone() {
        return isDone;
    }

    public void setDone(boolean done) {
        isDone = done;
    }

    public abstract String getTaskType();

    public abstract String getTime();

    @Override
    public String toString() {
        return "[" + getStatusIcon() + "] " + taskName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Task)) return false;
        Task task = (Task) o;
        return isDone() == task.isDone() && getTaskName().equals(task.getTaskName());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getTaskName(), isDone());
    }
}
