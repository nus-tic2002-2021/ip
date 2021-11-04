import java.text.ParseException;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.logging.Logger;

public class Parser {
    private static final Logger logger = Logger.getLogger(Logger.GLOBAL_LOGGER_NAME);

    /**
     * @param date
     * @return date
     */
    public LocalDate stringToDate(String date) {
        LocalDate parsedDate = null;
        try {
            parsedDate = LocalDate.parse(date);
        } catch (DateTimeParseException e) {
            logger.info("parse date time got error" + e.getMessage());
        }
        return parsedDate;
    }
}
