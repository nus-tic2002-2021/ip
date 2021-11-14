package dukeMain.tasks;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
/**
 * Represents a child of Task. A <code>Event</code> object includes
 * Localdate byDate and String byTime.
 * e.g., <code>"This is an Event Task",23-10-2019,2300hrs</code>
 */
public class Event extends Task {

    protected LocalDate byDate;
    protected String byTime;

    public Event(String description, LocalDate byDate , String byTime) {
        super(description);
        this.byDate = byDate;
        this.byTime = byTime;
    }

    public Event(boolean done,String description, LocalDate byDate , String byTime) {
        super(description,done);
        this.byDate = byDate;
        this.byTime = byTime;
    }
    /**
     * Overriding the Parent class's toString.
     * adding the "[E]" in front of the description
     * Also include LocalDate formatted in "MMM d yyyy" and
     * time in the given string format.
     *
     * @return String value
     */
    @Override
    public String toString() {
        String time = byTime.equals("") ? byTime : " " + byTime;
        return "[E]" + super.toString() + " (at: " + this.byDate.format(DateTimeFormatter.ofPattern("MMM d yyyy"))+time + ")";
    }

    /**
     * Overriding the Parent class's toString.
     * To format the Event tasks into a string format in the preferred separator.
     * Used for Saving purposes.
     *
     * @param separator String
     * @return String value
     */
    @Override
    public String toStringSaveTask(String separator ){
        separator = " " + separator + " ";
        return "E" + separator + super.toStringSaveTask(separator) +  separator +this.byDate + separator + byTime;
    }
}