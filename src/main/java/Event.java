import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.Objects;
import java.util.logging.Logger;

public class Event extends Todo {
    LocalDate at;
    private static final Logger logger = Logger.getLogger(Logger.GLOBAL_LOGGER_NAME);

    public Event(String description, String start, int id) {
        super(description, id);
        setAt(start);
    }

    public LocalDate parseStringToDate(String date) {
        LocalDate parsedDate = null;
        try {
            parsedDate = LocalDate.parse(date);
        } catch (DateTimeParseException e) {
            logger.info("parse date time got error" + e.getMessage());
        }
        return parsedDate;
    }

    public LocalDate getAt() {
        return at;
    }

    public void setAt(String start) {
        if (Objects.equals(start, "")) {
            return;
        }
        LocalDate date = parseStringToDate(start);
        if (date == null) {
            logger.info("parsing date got null");
        } else {
            at = date;
        }
    }

    @Override
    public String toString() {
        String status = isDone ? "X" : " ";
        return id + ". [E][" + status + "] " + super.getDescription() + " (at: " + at + ")";
    }

    @Override
    public String getType() {
        return "event";
    }
}