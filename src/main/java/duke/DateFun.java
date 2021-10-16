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
     * data convert to string
     * @param date
     * @return data string
     */
    public static String LocalDateToString(LocalDate date){
        DateTimeFormatter fmt = DateTimeFormatter.ofPattern("MMM dd yyyy");
        return date.format(fmt);
    }

}