package classes;

import classes.enums.TaskType;

public class Deadline extends Task {
    private String dueDate;

    public Deadline(String description, String dueDate) {
        super(TaskType.DEADLINE, description);
        this.setDueDate(dueDate);
    }

    public String getDueDate() {
        return dueDate;
    }

    public void setDueDate(String dueDate) {
        this.dueDate = dueDate;
    }

    @Override
    public String toStatusString() {
        return "[D]" + super.toStatusString() + " (by: " + dueDate + ")";
    }
}