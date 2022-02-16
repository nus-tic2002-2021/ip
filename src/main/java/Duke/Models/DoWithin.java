package Duke.Models;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

public class DoWithin extends Task {
    protected String start;
    protected LocalDate startDate;
    protected LocalTime startTime;
    protected String end;
    protected LocalDate endDate;
    protected LocalTime endTime;

    /**
     * A constructor for dowithin object that takes in three strings
     * one as the task description
     * and the other two as the start date/time and end date/time
     * of the "dowithin" task.
     * @param newTask
     * @param newStart
     * @param newEnd
     */
    public DoWithin(String newTask, String newStart, String newEnd) {
        super(newTask);
        start = newStart;
        end = newEnd;
    }

    /**
     * A constructor for dowithin that takes in
     * three strings, two localtime and two localdate objects
     * newTask string representing the task description
     * newStart, newStartDate and newStartTime representing the starting date/time of task
     * newEnd, newEndDate and newEndTime representing the ending date/time of task
     * @param newTask
     * @param newStart
     * @param newEnd
     * @param newStartDate
     * @param newStartTime
     * @param newEndDate
     * @param newEndTime
     */
    public DoWithin (String newTask, String newStart, String newEnd, LocalDate newStartDate,
                     LocalTime newStartTime, LocalDate newEndDate, LocalTime newEndTime) {
        super(newTask.trim());
        if (newStartDate != null) {
            startDate = newStartDate;
            startTime = newStartTime;
        } else {
            start = newStart.trim();
        }
        if (newEndTime != null) {
            endDate = newEndDate;
            endTime = newEndTime;
        } else {
            end = newEnd.trim();
        }
    }

    /**
     * Get the start date and time
     * and end date and time of the deadline.
     * @return
     */
    public String getAdditionalInfo() {
        String info;
        if (startDate == null) {
            info = start;
        } else {
            info = startDate.toString();
            if (startTime != null) {
                info = info + " " + startTime;
            }
        }
        info = info + " | ";
        if (endDate == null) {
            info = info + end;
        } else {
            info = info + endDate.toString();
            if (endTime != null)
                info = info + " " + endTime;
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
            return "[W][X]";
        } else {
            return "[W][ ]";
        }
    }

    /**
     * Return string with information of the "do within" task.
     * Date is given in a new format if "dowithin" has a LocalDate object.
     * @return String with information on the event of the task.
     */
    public String getTaskInfo() {
        String info = super.getTaskInfo() + " (between: ";
        if (startDate == null) {
            info = info + start;
        } else {
            info = info + startDate.format(DateTimeFormatter.ofPattern("MMM dd yyyy"));
            if (startTime != null) {
                info = info + " " + startTime;
            }
        }
        info = info + " and ";
        if (endDate == null) {
            info = info + end;
        } else {
            info = info + endDate.format(DateTimeFormatter.ofPattern("MMM dd yyyy"));
            if (endTime != null) {
                info = info + " " + endTime;
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
        return "W";
    }

}
