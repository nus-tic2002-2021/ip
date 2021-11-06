package Duke.Models;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

/**
 * Represents Events the user has to attend
 * with the description of the event and date and time of the event.
 */
public class Event extends Task {
    protected String at;
    protected LocalDate date;
    protected LocalTime time;


    /**
     * A constructor for event object that takes in two strings
     * one as the task description
     * and the other as the date/time of event.
     * @param newTask
     * @param datetime
     */
    public Event(String newTask, String datetime) {
        super(newTask);
        at = datetime;
    }

    /**
     * A constructor for event object that takes in a string and LocalDate object
     * where the string is the task description
     * and the LocalDate is the date of the event.
     * @param newTask
     * @param newDate
     */
    public Event(String newTask, LocalDate newDate) {
        super(newTask);
        date = newDate;
    }

    /**
     * A constructor for event object that takes in a string, LocalDate and LocalTime object
     * where the string is the task description,
     * the LocalDate is the date of the event
     * and the LocalTime is the time of the event.
     * @param newTask
     * @param newDate
     */
    public Event(String newTask, LocalDate newDate, LocalTime newTime) {
        super(newTask);
        date = newDate;
        time = newTime;
    }

    /**
     * Get the date and time information of the event.
     * @return String with date and time of the event.
     */
    public String getAdditionalInfo() {
        String info;
        if (date == null) {
            info = at;
        } else {
            info = date.toString();
            if (time != null) {
                info = info + " " + time;
            }
        }
        return info;
    }

    /**
     * Gets a string with task type symbol
     * and a cross symbol to indicate whether a task is complete.
     * @return String with task type and completed symbol.
     */
    public String getCompletedSymbol() {
        if (Completed) {
            return "[E][X]";
        } else {
            return "[E][ ]";
        }
    }

    /**
     * Return string of date time information.
     * Date is given in a new format if event has a LocalDate object.
     * @return String with information on the event of the task.
     */
    public String getTaskInfo() {
        String info = super.getTaskInfo() + " (at: ";
        if (date == null) {
            info = info + at;
        } else {
            info = info + date.format(DateTimeFormatter.ofPattern("MMM dd yyyy"));
            if (time != null) {
                info = info + " " + time;
            }
        }
        info = info + ")";
        return info;
    }

    /**
     * Returns task type symbol.
     * @return String with initials of the task type.
     */
    @Override
    public String getTaskType() {
        return "E";
    }

    /**
     * Prints event date in "MMM, dd, yyyy" format.
     * Only works if event object has a LocalTime variable.
     */
    public void printDate() {
        System.out.println(date.format(DateTimeFormatter.ofPattern("MMM dd yyyy")));
    }
}
