package dukeMain.tasks;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

public class Deadline extends Task {

    protected LocalDate byDate;
    protected String byTime;

//    public Deadline(String description, LocalDate byDate) {
//        super(description);
//        this.byDate = byDate;
//        this.byTime = "";
//    }
    public Deadline(String description, LocalDate byDate , String byTime) {
        super(description);
        this.byDate = byDate;
        this.byTime = byTime;
    }
//    public Deadline(boolean done,String description, LocalDate byDate) {
//        super(description,done);
//        this.byDate = byDate;
//        this.byTime = "";
//    }
    public Deadline(boolean done,String description, LocalDate byDate , String byTime) {
        super(description,done);
        this.byDate = byDate;
        this.byTime = byTime;
    }

    @Override
    public String toString() {
        String time = byTime.equals("") ? byTime : " " + byTime;
        return "[D]" + super.toString() + " (by: " + this.byDate.format(DateTimeFormatter.ofPattern("MMM d yyyy"))+time + ")";
    }

    @Override
    public String toStringSaveTask(String separator ){
        separator = " " + separator + " ";
        return "D" + separator + super.toStringSaveTask(separator) + separator +this.byDate + separator + byTime;
    }
}