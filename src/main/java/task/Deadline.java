package task;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;


public class Deadline extends Task{
    private final String PRINT_FORMAT = "MMM d yyyy HH:mm a";
    private final String SAVE_FORMAT = "yyyy-MM-dd HHmm";
    private final String DATE_FORMAT = "yyyy-MM-dd";
    protected LocalDateTime by;
    /**
     * Constructor for a new deadline created by user command
     * sets the description and by of the task to be description and by argument respectively
     * and isDone is always false for newly added task.
     * @param description description of the task
     * @param by date of task to be completed by
     */
    public Deadline(String description, LocalDateTime by) {
        super(description);
        setBy(by);
        setType();
    }
    /**
     * Constructor for a new task created by loading file
     * sets the description and by of the task to be description and by argument respectively.
     * isDone argument determines whether task isDone is true or false.
     * @param description description of the task
     * @param by date of task to be completed by
     * @param isDone is program set as done
     */
    public Deadline(String description, LocalDateTime by, Boolean isDone) {
        super(description);
        setBy(by);
        setType();
        this.isDone = isDone;
    }

    public void setDone(){
        this.isDone = true;
        System.out.println("Nice! I've marked this task as done:\n" +
                "  [D][X] " + getDescription() + "(by: " + getByFormat() + ")");
    }
    public void setType(){
        type = Action.DEADLINE;
    }

    public void print(){
        if (isDone) {
            System.out.println("  [D][X] " + getDescription() + "(by: " + getByFormat() + ")");
        } else {
            System.out.println("  [D][ ] " + getDescription() + "(by: " + getByFormat() + ")");
        }

    }
    public void setBy(LocalDateTime by)
    {
        this.by = by;
    }

    public String getTask(){
        return "D";
    }
    public String getDescription(){
        return description;
    }
    public String getByFormat(){
        return by.format(DateTimeFormatter.ofPattern(PRINT_FORMAT));
    }
    public LocalDateTime getDateTime() {
        return by;
    }
    /**
     * Returns the format of the task to be saved.
     * gets task, isDone and description separated by ' | '
     * @return the file format
     */
    public String getSave(){
        String s = getTask() + " | " +  getDone() + " | " + getDescription() + " | " +
                getDateTime().format(DateTimeFormatter.ofPattern(SAVE_FORMAT));
        return s;
    }

    @Override
    public String toString() {
        String box = "[D][ ] ";
        if (isDone){
            box = "[D][X] ";
        }
        return (box + description + "(by: " + getByFormat() + ")");
    }
}