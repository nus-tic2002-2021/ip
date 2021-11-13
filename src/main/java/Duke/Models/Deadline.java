package Duke.Models;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

/**
 * Represents a task with a deadline
 * with a description of the task
 * and the date and time when it is due.
 */
public class Deadline extends Task {
    protected String by;
    protected LocalDate date;
    protected LocalTime time;

    /**
     * A constructor for deadline object that takes in two strings
     * one as the task description
     * and the other as the deadline of the deadline object.
     * @param newTask
     * @param datetime
     */
    public Deadline(String newTask, String datetime) {
        super(newTask);
        by = datetime;
    }

    /**
     * A constructor for deadline object that takes in a string and LocalDate object
     * where the string is the task description
     * and the LocalDate is the date the task is due.
     * @param newTask
     * @param newDate
     */
    public Deadline(String newTask, LocalDate newDate) {
        super(newTask);
        date = newDate;
    }

    /**
     * A constructor for deadline object that takes in a string and LocalDate object
     * where the string is the task description,
     * the LocalDate is the date the task is due
     * and the LocalTime is the time the task is due.
     * @param newTask
     * @param newDate
     */
    public Deadline(String newTask, LocalDate newDate, LocalTime newTime) {
        super(newTask);
        date = newDate;
        time = newTime;
    }

    /**
     * Get the date and time of the deadline.
     * @return
     */
    public String getAdditionalInfo() {
        String info;
        if (date == null) {
            info = by;
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
            return "[D][X]";
        } else {
            return "[D][ ]";
        }
    }

    /**
     * Return string with information of the deadline.
     * Date is given in a new format if event has a LocalDate object.
     * @return String with information on the deadline of the task.
     */
    public String getTaskInfo() {
        String info = super.getTaskInfo() + " (by: ";
        if (date == null) {
            info = info + by;
        } else {
            info = info + date.format(DateTimeFormatter.ofPattern("MMM dd yyyy"));
            if (time != null) {
                info = info + " " + time;
            }
        }
        info = info + ")";
        info = info + " - with " + Priority + " priority";
        return info;
    }

    /**
     * Returns task type symbol.
     * @return String with initials of the task type.
     */
    @Override
    public String getTaskType() {
        return "D";
    }

    /**
     * Prints event date in "MMM, dd, yyyy" format.
     * Only works if event object has a LocalTime variable.
     */
    public void printDate() {
        System.out.println(date.format(DateTimeFormatter.ofPattern("MMM dd yyyy")));
    }
}
