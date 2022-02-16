package parser;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Parser {

    public static String parseDateForStorage(LocalDateTime dateNTime)
    {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d/M/yyyy HHmm");
        return dateNTime.format(formatter);
    }

    public static LocalDateTime parseStringDateTimetoLocaLDateTime (String dateNTime)
    {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d/M/yyyy HHmm");
        return LocalDateTime.parse(dateNTime, formatter);
    }
    public static LocalDate parseStringDatetoLocaLDate (String dateNTime)
    {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d/M/yyyy");
        return LocalDate.parse(dateNTime, formatter);
    }
    public static String parseDateForDisplay (LocalDateTime dateNTime)
    {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMM dd yyyy hhmm a");
        return dateNTime.format(formatter);
    }
}
