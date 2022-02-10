package task;

import java.time.LocalDateTime;
import java.time.LocalTime;

abstract class Task {
    protected String description;
    protected Boolean isDone;
    protected Action type;

    /**
     * Constructor for a new task created by user command
     * sets the description of the task to be description argument and isDone is always
     * false for newly added task.
     *
     * @param description description of the task
     */
    public Task(String description) {
        setDescription(description);
        isDone = false;
    }

    /**
     * Constructor for a new task created by loading file
     * sets the description of the task to be description argument.
     * isDone argument determines whether task isDone is true or false.
     *
     * @param description description of the task
     * @param isDone      is program set as done
     */
    public Task(String description, Boolean isDone) {
        setDescription(description);
        this.isDone = isDone;
    }

    public void setDescription(String description) {
        this.description = description;
    }


    public abstract void setDone();

    public abstract void print();

    public abstract void setType();

    public abstract String getTask();

    public abstract String getDescription();

    public abstract String getSave();

    public LocalDateTime getDateTime() {
        return null;
    }

    public LocalTime getTime() {
        return null;
    }

    public Action getType() {
        return type;
    }

    public String getDone() {
        if (isDone) {
            return "1";
        } else {
            return "0";
        }
    }

}
