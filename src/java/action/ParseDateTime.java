package java.action;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;
import java.time.format.DateTimeParseException;
import java.ui.Message;
import java.ui.Ui;
import java.util.Locale;

/**
 * Handle date and time related functions
 * <p>
 * Conversion between String and LocalDate/LocalTime
 * String manipulation
 *
 * @author Kang Teng
 * @version 8.0
 * @since 2021-09-01
 */

public class ParseDateTime {

    private static Ui ui = new Message();

    /**
     * Convert date from String to LocalDate
     *
     * @param date TaskDate of the task
     * @return LocalDate
     * @throws DateTimeParseException If format of the date is not accepted
     */
    public static LocalDate toDate(String date) {

        LocalDate formattedDate;
        DateTimeFormatterBuilder builder = new DateTimeFormatterBuilder()
                .parseCaseInsensitive()
                .parseLenient()
                .appendPattern("[yyyy-MM-dd]")
                .appendPattern("[M-d-yyyy]")
                .appendPattern("[M-dd-yyyy]")
                .appendPattern("[MM-d-yyyy]")
                .appendPattern("[MM-dd-yyyy]")
                .appendPattern("[M/d/yyyy]")
                .appendPattern("[M/dd/yyyy]")
                .appendPattern("[MM/d/yyyy]")
                .appendPattern("[MM/dd/yyyy]")
                .appendPattern("[d-M-yyyy]")
                .appendPattern("[d-MM-yyyy]")
                .appendPattern("[dd-M-yyyy]")
                .appendPattern("[dd-MM-yyyy]")
                .appendPattern("[d/M/yyyy]")
                .appendPattern("[d/MM/yyyy]")
                .appendPattern("[dd/M/yyyy]")
                .appendPattern("[dd/MM/yyyy]")
                .appendPattern("[MMM dd yyyy]");

        DateTimeFormatter formatter = builder.toFormatter(Locale.ENGLISH);

        try {
            formattedDate = LocalDate.parse(date, formatter);
            return formattedDate;
        } catch (DateTimeParseException e) {
            throw e;
        }
    }

    /**
     * Convert time from String to LocalTime
     *
     * @param time TaskDate of the task
     * @return LocalTime
     * @throws DateTimeParseException If format of the date is not accepted
     */
    public static LocalTime toTime(String time) {
        LocalTime localTime = LocalTime.parse(time);
        return localTime;
    }

    /**
     * Split Date and Time by empty space " "
     *
     * @param dateAndTime String that contains both date and time
     * @return String[]
     */
    public static String[] splitDateAndTime(String dateAndTime) {
        return dateAndTime.split(" ");
    }

    /**
     * Check if the string contains date and time
     *
     * return 1 if the string contains only string representation of a Date
     * return 2 if the string contains string representation of a Date and a Time
     * return 3 if the string contains string representation of a Date and 2 Time
     *
     * @param dateAndTime string representation of date and time
     * @return int 1 or 2 or 3
     */
    public static int isDateAndTime(String dateAndTime) {
        String[] split = dateAndTime.split(" ");
        if (split.length == 1) {
            return 1;
        } else if (split.length == 2) {
            return 2;
        } else {
            return 3;
        }
    }

    /**
     * Extract string representation of date from string array
     *
     * @param split string array of date and time
     * @return String Date
     */
    public static String toExtractDateFromSplitDateAndTime(String[] split) {
        return split[0];
    }

    /**
     * Extract string representation of time from string array
     *
     * @param split string array of date and time
     * @return String Time
     */
    public static String toExtracTimeFromSplitDateAndTime(String[] split) {
        return split[1];
    }

    /**
     * Extract string representation of start time from string array
     *
     * @param split string array of date and time
     * @return String TimeStart
     */
    public static String toExtracTimeStartFromSplitDateAndTime(String[] split) {
        return split[1];
    }

    /**
     * Extract string representation of end time from string array
     *
     * @param split string array of date and time
     * @return String TimeEnd
     */
    public static String toExtracTimeEndFromSplitDateAndTime(String[] split) {
        return split[2];
    }
}
