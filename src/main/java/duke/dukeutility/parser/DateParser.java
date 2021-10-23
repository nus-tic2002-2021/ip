package duke.dukeutility.parser;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

import duke.dukeexception.DukeParseDateTimeException;

public class DateParser {
    private static final String transitiveJsonAndTextPattern = "yyyy-MM-dd-HH-mm-ss";
    private static final List<String> patterns =
        List.of("yyyyMMdd", "yyyyMMdd HH:mm", DateParser.transitiveJsonAndTextPattern);

    public static String parseLocalDateTimeAsString(LocalDateTime ldt) {
        return DateTimeFormatter.ofPattern(DateParser.transitiveJsonAndTextPattern).format(ldt);
    }

    /**
     * format string as LocalDateTime
     *
     * @param dateTimeString
     * @return date in LocalDateTime format.
     * @throws DukeParseDateTimeException Formatting failed
     */
    public static LocalDateTime parseStringAsLocalDateTime(String dateTimeString) throws DukeParseDateTimeException {

        LocalDateTime ldt = null;
        for (String pattern : DateParser.patterns) {
            try {
                ldt = LocalDateTime.parse(dateTimeString, DateTimeFormatter.ofPattern(pattern));
            } catch (Exception e) {
                ;
            }
        }

        try {
            ldt = parseStringAsLocalDate(dateTimeString).atTime(0, 0);
        } catch (Exception e) {
            ;
        }
        if (ldt == null) {
            throw new DukeParseDateTimeException(
                "Date should be of the following format: " + String.join(", ", patterns));
        }
        return ldt;
    }

    public static LocalDate parseStringAsLocalDate(String dateString) {
        return LocalDate.parse(dateString, DateTimeFormatter.ofPattern("yyyyMMdd"));
    }

    public static String prettifyLocalDateTime(LocalDateTime ldt) {
        return ldt.toString().replace("T", " ");
    }
}
