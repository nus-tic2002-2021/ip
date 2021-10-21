package duke.testHelper.help;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;



public class ParserUnderTest {
    private static final String transitiveJsonAndTextPattern = "yyyy-MM-dd-HH-mm-ss";
    private static final List<String> patterns = List.of("yyyyMMdd", "yyyyMMdd HH:mm", ParserUnderTest.transitiveJsonAndTextPattern);

    public static String parseLocalDateTimeAsString(LocalDateTime ldt) {
        return DateTimeFormatter.ofPattern(ParserUnderTest.transitiveJsonAndTextPattern).format(ldt);
    }

    public static LocalDateTime parseStringAsLocalDateTime(String dateTimeString) throws Exception {

        LocalDateTime ldt = null;
        for (String pattern : ParserUnderTest.patterns) {
            try {
                ldt = LocalDateTime.parse(dateTimeString, DateTimeFormatter.ofPattern(pattern));
            } catch (Exception e) {

            }
        }
        try {
            ldt = parseStringAsLocalDate(dateTimeString).atTime(0, 0);
        } catch (Exception e) {
        }
        if (ldt == null) {
            throw new Exception("Parse as LocalDateTime failed.");
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
