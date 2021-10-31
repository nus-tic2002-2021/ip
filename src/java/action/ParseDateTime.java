package src.java.action;

import src.java.ui.Message;
import src.java.ui.Ui;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;
import java.time.format.DateTimeParseException;
import java.time.temporal.ChronoField;
import java.util.Locale;

public class ParseDateTime {

    private static Ui ui = new Message();

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

    public static LocalTime toTime(String time){
        LocalTime localTime = LocalTime.parse(time);
        return localTime;
    }

    public static String[] splitDateAndTime(String dateAndTime) {
        return dateAndTime.split(" ");
    }

    public static int isDateAndTime(String dateAndTime) {
        String[] split = dateAndTime.split(" ");
        if (split.length == 1) {
            return 1;
        } else if (split.length == 2){
            return 2;
        } else {
            return 3;
        }
    }

    public static String ExtractDateFromSplitDateAndTime(String[] split) {
        return split[0];
    }

    public static String ExtracTimeFromSplitDateAndTime(String[] split) {
        return split[1];
    }

    public static String ExtracTimeStartFromSplitDateAndTime(String[] split) {
        return split[1];
    }

    public static String ExtracTimeEndFromSplitDateAndTime(String[] split) {
        return split[2];
    }

    public static void formatDateExample() {

        // https://coderanch.com/t/677142/java/DateTimeParseException-Text-parsed-unparsed-text

        String d2arr[] = {
                "2016-12-21",
                "1/17/2016",
                "1/3/2016",
                "11/23/2016",
                "OCT 20 2016",
                "Oct 22 2016",
                "Oct 23", // default year is 2016
                "OCT 24",  // default year is 2016
        };

        DateTimeFormatterBuilder builder = new DateTimeFormatterBuilder()
                .parseCaseInsensitive().parseLenient()
                .parseDefaulting(ChronoField.YEAR_OF_ERA, 2016L)
                .appendPattern("[yyyy-MM-dd]")
                .appendPattern("[M/dd/yyyy]")
                .appendPattern("[M/d/yyyy]")
                .appendPattern("[MM/dd/yyyy]")
                .appendPattern("[MMM dd yyyy]");

        DateTimeFormatter formatter2 = builder.toFormatter(Locale.ENGLISH);
        for (String d2 : d2arr) {
            try {
                LocalDate date = LocalDate.parse(d2, formatter2);
                System.out.printf("%s%n", date);
            } catch (DateTimeParseException e) {
                System.out.printf("%s is not parsable! %n", d2);
                throw e;
            }
        }
    }
}
