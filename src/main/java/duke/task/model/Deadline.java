package duke.task.model;

public final class Deadline extends Task {
    private String _deadline;

    public Deadline(String taskDescription, String deadline, Integer taskId, Boolean done) {
        super(taskDescription, taskId, done);
        this.setDeadline(deadline);
    }

    private Deadline() {
    }

    private void setDeadline(String deadline) {
        this._deadline = deadline;
    }

    public String getDeadline() {
        return this._deadline;
    }



    public String getChronologyString() {
        return "By: " + this.getDeadline();
    }
}
