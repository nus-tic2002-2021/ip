package task;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;


public class Deadline extends Task{
    private final String PRINT_FORMAT = "MMM d yyyy HH:mm a";
    private final String SAVE_FORMAT = "yyyy-MM-dd HHmm";
    private final String DATE_FORMAT = "yyyy-MM-dd";
    protected LocalDateTime by;

    public Deadline(String description, LocalDateTime by) {
        super(description);
        setBy(by);
    }

    public Deadline(String description, LocalDateTime by, Boolean isDone) {
        super(description);
        setBy(by);
        this.isDone = isDone;
    }

    public void setDone(){
        this.isDone = true;
        System.out.println("\tNice! I've marked this task as done:\n" +
                "\t  [D][X] " + getDescription() + "(by: " + getByFormat() + ")");
    }
    public void print(){
        if (isDone) {
            System.out.println("\t  [D][X] " + getDescription() + "(by: " + getByFormat() + ")");
        } else {
            System.out.println("\t  [D][ ] " + getDescription() + "(by: " + getByFormat() + ")");
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
    public String getDate(){
        return by.format(DateTimeFormatter.ofPattern(DATE_FORMAT));
    }
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