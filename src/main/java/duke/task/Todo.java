package duke.task;

public class Todo extends Task {

    public Todo(String taskName) {
        super(taskName);
    }

    public String getTaskType() { return "TODO"; }

    @Override
    public String getTime() {
        return null;
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
