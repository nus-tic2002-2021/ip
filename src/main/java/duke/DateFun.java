package duke;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

public class DateFun {

    public static LocalDate stringToLocalDate(String timeStr){
        DateTimeFormatter fmt = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate date = LocalDate.parse(timeStr, fmt);
        return date;
    }

    /**
     * date convert to string
     * @param date: a LocalDate variable
     * @return  a string which shows date
     */
    public static String LocalDateToString(LocalDate date){
        DateTimeFormatter fmt = DateTimeFormatter.ofPattern("MMM dd yyyy");
        return date.format(fmt);
    }

}