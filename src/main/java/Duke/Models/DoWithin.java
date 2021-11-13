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

    public DoWithin(String newTask, String newStart, String newEnd) {
        super(newTask);
        start = newStart;
        end = newEnd;
    }

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

    public String getCompletedSymbol() {
        if (Completed) {
            return "[W][X]";
        } else {
            return "[W][ ]";
        }
    }

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

    @Override
    public String getTaskType() {
        return "W";
    }

}
