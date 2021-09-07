package duke.coronet.dukeUtility.parser;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

public class DateParser {
    private static final String transitiveJsonAndTextPattern = "yyyy-MM-dd-HH-mm-ss";
    private static final List<String> patterns = List.of("yyyyMMdd", "yyyyMMdd HH:mm", DateParser.transitiveJsonAndTextPattern);

    public static LocalDateTime parseStringAsLocalDateTime(String dateTimeString) throws Exception {
        for (String pattern : DateParser.patterns) {
            try {
                return LocalDateTime.parse(dateTimeString, DateTimeFormatter.ofPattern(pattern));
            } catch (Exception e) {
            }
        }
        try {
            return parseStringAsLocalDate(dateTimeString).atTime(0, 0);
        } catch (Exception e) {
        }
        throw new Exception("Parse as LocalDateTime failed.");
    }
    public static LocalDate parseStringAsLocalDate(String dateString) {
        return LocalDate.parse(dateString, DateTimeFormatter.ofPattern("yyyyMMdd"));
    }
    public static String prettifyLocalDateTime(LocalDateTime ldt) {
        return ldt.toString().replace("T", " ");
    }
}
