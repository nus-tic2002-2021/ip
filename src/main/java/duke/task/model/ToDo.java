package duke.task.model;


public final class ToDo extends Task {
    public ToDo(String taskDescription, Integer taskId, Boolean done) {
        super(taskDescription, taskId, done);
    }

    public String getChronologyString() {
        return ("-");
    }

    private ToDo() {
    }
}
