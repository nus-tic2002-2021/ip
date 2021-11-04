import java.sql.Time;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Objects;
import java.util.logging.Logger;

public class Event extends Deadline {
    long startTime;

    private static final Logger logger = Logger.getLogger(Logger.GLOBAL_LOGGER_NAME);

    public Event(String description, String date, int id) {
        super(description, date, id);
    }

    @Override
    public String toString() {
        String status = isDone ? "X" : " ";
        return id + ". [E][" + status + "] " + super.getDescription() + " (at: " + date.format(DateTimeFormatter.ofPattern("MMM d yyyy")) + ")";
    }

    @Override
    public String getType() {
        return "event";
    }
}