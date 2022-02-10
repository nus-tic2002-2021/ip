package task;

public class Todo extends Task {
    /**
     * Constructor for a new to do created by user command
     * sets the description of the task to be description argument and isDone is always
     * false for newly added task. Type is set to TODO.
     *
     * @param description description of the task
     */
    public Todo(String description) {
        super(description);
        setType();
    }

    /**
     * Constructor for a new task created by loading file
     * sets the description of the task to be description argument.
     * isDone argument determines whether task isDone is true or false.
     *
     * @param description description of the task
     */
    public Todo(String description, Boolean isDone) {
        super(description);
        this.isDone = isDone;
        setType();
    }

    public void setDone() {
        this.isDone = true;
        System.out.println("Nice! I've marked this task as done:\n" +
                "  [T][X] " + getDescription());
    }

    public void setType() {
        type = Action.TODO;
    }

    public void print() {
        if (isDone) {
            System.out.println("  [T][X] " + getDescription());
        } else {
            System.out.println("  [T][ ] " + getDescription());
        }
    }

    public String getTask() {
        return "T";
    }

    public String getDescription() {
        return description;
    }

    /**
     * Returns the format of the task to be saved.
     * gets task, isDone and description separated by ' | '
     *
     * @return the file format
     */
    public String getSave() {
        String s = getTask() + " | " + getDone() + " | " + getDescription();
        return s;
    }

    @Override
    public String toString() {
        String box = "[T][ ] ";
        if (isDone) {
            box = "[T][X] ";
        }
        return (box + getDescription());
    }
}
