package duke.coronet.testHelper.help.parser;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

public class DateParserUnderTest {
    private static final String transitiveJsonAndTextPatternUnderTest = "yyyy-MM-dd-HH-mm-ss";
    private static final List<String> datePatternsUnderTest = List.of("yyyyMMdd", "yyyyMMdd HH:mm", DateParserUnderTest.transitiveJsonAndTextPatternUnderTest);

    public static LocalDateTime parseStringAsLocalDateTimeUnderTest(String dateTimeString) throws Exception {

        for (String pattern : DateParserUnderTest.datePatternsUnderTest) {
            try {
                return LocalDateTime.parse(dateTimeString, DateTimeFormatter.ofPattern(pattern));
            } catch (Exception e) {
            }
        }
        try {
            return parseStringAsLocalDateUnderTest(dateTimeString).atTime(0, 0);
        } catch (Exception e) {
        }
            throw new Exception("Parse as LocalDateTime failed.");

    }
    public static LocalDate parseStringAsLocalDateUnderTest(String dateString) {
        return LocalDate.parse(dateString, DateTimeFormatter.ofPattern("yyyyMMdd"));
    }
    public static String prettifyLocalDateTimeUnderTest(LocalDateTime ldt) {
        return ldt.toString().replace("T", " ");
    }
}
