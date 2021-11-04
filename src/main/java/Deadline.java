import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Objects;
import java.util.logging.Logger;

public class Deadline extends Todo {
    LocalDate date;
    long endTime;

    private static final Logger logger = Logger.getLogger(Logger.GLOBAL_LOGGER_NAME);

    public Deadline(String description, String end, int id) {
        super(description, id);

        ArrayList<String> tokens = new ArrayList(Arrays.asList(end.split(" ")));
        setDate(tokens.get(0));
        setEndTime(tokens.get(1));
    }

    public Deadline(String description, int id) {
        super(description, id);
        date = null;
    }

    public Deadline(String description, LocalDate date, long time, int id) {
        super(description, id);
        this.date = date;
        endTime = time;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(String date) {
        if (Objects.equals(date, "")) {
            return;
        }
        LocalDate parsedDate = handlerParseStringToDate(date);
        if (parsedDate == null) {
            logger.info("parsing date got null");
        } else {
            this.date = parsedDate;
        }
    }

    public void setEndTime(String time) {
        DateFormat formatter = new SimpleDateFormat("HH:mm");
        try {
            this.endTime = formatter.parse(time).getTime();
        } catch (ParseException p) {
            logger.info("parsing time got null");
        }
    }

    public long getEndTime() {
        return endTime;
    }

    @Override
    public String toString() {
        String status = isDone ? "X" : " ";
        return id + ". [D][" + status + "] " + super.getDescription() + " (at: " + date.format(DateTimeFormatter.ofPattern("MMM d yyyy")) + ")";
    }

    @Override
    public String getType() {
        return "deadline";
    }

    public LocalDate handlerParseStringToDate(String date) {
        LocalDate parsedDate = null;
        try {
            parsedDate = LocalDate.parse(date);
        } catch (DateTimeParseException e) {
            logger.info("parse date time got error" + e.getMessage());
        }
        return parsedDate;
    }
}