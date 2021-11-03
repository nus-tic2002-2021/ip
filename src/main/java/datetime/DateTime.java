package datetime;

import java.time.format.DateTimeFormatter;
import java.time.LocalTime;
import java.time.LocalDateTime;
import exception.DukeException;

import java.time.format.DateTimeParseException;
import java.util.ArrayList;

/**
 * Wrapper of LocalDateTime.
 * Stores date and time for Task.
 */
public class DateTime {
    private LocalTime endDateTime;
    public LocalDateTime startDateTime;
    public String displayDateTime;
    private static DateTimeFormatter getTime = DateTimeFormatter.ofPattern("HHmm");
    private static DateTimeFormatter getStartDateTime = DateTimeFormatter.ofPattern("d/MM/yyyy HHmm");

    /**
     * Calls during construction of Deadline object.
     * Reads substring from user input and gets date and time of task.
     * @param dateTime Must be in the form (d/MM/yyyy HHmm)
     * @return DateTime object as dateTime parameter of Deadline object.
     * @throws DukeException Throws if dateTime is not presented in the correct form.
     */
    public static DateTime setDeadline(String dateTime) throws DukeException {
        try {
            DateTime deadlineDateTime = new DateTime();
            deadlineDateTime.startDateTime = LocalDateTime.parse(dateTime, getStartDateTime);
            deadlineDateTime.displayDateTime = deadlineDateTime.startDateTime.format(getStartDateTime);
            return deadlineDateTime;
        } catch (DateTimeParseException e) {
            throw new DukeException("Incorrect deadline datetime format."
                    + "\nPlease key in deadline (details) /by (date)d/mm/yyyy (time)HHmm.");
        }
    }

    /**
     * Calls during construction of Deadline object.
     * Reads substring from text file and gets date and time of the task.
     * @param dateTime Must be in the form (d/MM/yyyy HHmm).
     * @return DateTime object as dateTime parameter of Deadline object.
     */
    public static DateTime readDeadLine(String dateTime) {
        DateTime deadlineDateTime = new DateTime();
        deadlineDateTime.startDateTime = LocalDateTime.parse(dateTime, getStartDateTime);
        deadlineDateTime.displayDateTime = deadlineDateTime.startDateTime.format(getStartDateTime);
        return deadlineDateTime;
    }

    /**
     * Calls during construction of Event object.
     * Reads substring from user input and gets date, start time and end time of task.
     * @param dateTime Must be in the form (d/MM/yyyy HHmm-HHmm).
     * @return DateTime object as dateTime parameter of Event object.
     * @throws DukeException Throws if missing end time or if dateTime is not presented in the correct form.
     */
    public static DateTime setEventTime(String dateTime) throws DukeException {
        try {
            DateTime eventDateTime = new DateTime();
            int divider = dateTime.indexOf("-");
            if (divider == -1 || (divider == dateTime.length() - 1)) {
                throw new DukeException("Please key in the end time.");
            }
            String end = dateTime.substring(divider + 1, dateTime.length());
            eventDateTime.startDateTime = LocalDateTime.parse(dateTime.substring(0, divider), getStartDateTime);
            eventDateTime.endDateTime = LocalTime.parse(end, DateTimeFormatter.ofPattern("HHmm"));
            eventDateTime.displayDateTime = eventDateTime.startDateTime.format(getStartDateTime) + "-"
                    + eventDateTime.endDateTime.format(getTime);
            return eventDateTime;
        } catch (DateTimeParseException e) {
            throw new DukeException("Incorrect event format."
                    + "\nPlease key in event (details) /at (date)d/mm/yyyy (start time)HHmm-(end time)HHmm.");
        }
    }

    /**
     * Calls during construction of Event object.
     * Reads substring from text file and gets date, start time and end time of task.
     * @param dateTime Must be in the form (d/M/yyyy HHmm-HHmm)
     * @return DateTime object as dateTime parameter of Event object.
     * @throws DukeException Throws if missing end time. This should not happen unless text file was tampered with.
     */
    public static DateTime readEventTime(String dateTime) throws DukeException {
        DateTime eventDateTime = new DateTime();
        int divider = dateTime.indexOf("-");
        if (divider == -1 || (divider == dateTime.length() - 1)
                || dateTime.substring(divider + 1).replace(" ", "").equals("")) {
            throw new DukeException("Please key in the end time.");
        }
        String end = dateTime.substring(divider + 1, dateTime.length());
        eventDateTime.startDateTime = LocalDateTime.parse(dateTime.substring(0,divider), getStartDateTime);
        eventDateTime.endDateTime = LocalTime.parse(end, DateTimeFormatter.ofPattern("HHmm"));
        eventDateTime.displayDateTime = (eventDateTime.startDateTime.format(getStartDateTime) + "-"
                + eventDateTime.endDateTime.format(getTime));
        return eventDateTime;
    }

     /**
     * Adds suffix to date of month.
     * @param dd day of month as a 2 digits number.
     * @return 1 or 2 digits number with appropriate suffix.
     */private static String getDayOfMonth(String dd) {
        if (dd.substring(0, 1).equals("0")) {
            dd = dd.substring(1, dd.length());
        }
        int day = Integer.parseInt(dd);
        if (day >= 11 && day <= 31) {
            return day + "th";
        }
        switch (day & 10) {
        case 1:
            return day + "st";
        case 2:
            return day + "nd";
        case 3:
            return day + "rd";
        default:
            return day + "th";
        }
    }

    public String toString() {
        return displayDateTime;
    }
}
