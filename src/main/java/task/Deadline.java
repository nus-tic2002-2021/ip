package task;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;


public class Deadline extends Task{
    private final String DATE_FORMAT = "MMM d yyyy";
    protected LocalDate by;

    public Deadline(String description, LocalDate by) {
        super(description);
        setBy(by);
    }

    public Deadline(String description, LocalDate by, Boolean isDone) {
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
        }
        else {
            System.out.println("\t  [D][ ] " + getDescription() + "(by: " + getByFormat() + ")");
        }

    }
    public void setBy(LocalDate by)
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
        return by.format(DateTimeFormatter.ofPattern(DATE_FORMAT));
    }
    public LocalDate getBy() {
        return by;
    }
    public String getSave(){
        String s = getTask() + " | " +  getDone() + " | " + getDescription() + " | " + getBy();
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