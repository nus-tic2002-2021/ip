import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.Objects;
import java.util.concurrent.TimeUnit;

public class Event extends Deadline {
    protected long startTime;
    private static final Logger logger = new Logger();

    public String getStartTime() {
        assert startTime != 0;
        return String.format("%02d:%02d",
                TimeUnit.MILLISECONDS.toHours(startTime),
                TimeUnit.MILLISECONDS.toMinutes(startTime) -
                        TimeUnit.HOURS.toMinutes(TimeUnit.MILLISECONDS.toHours(startTime)));
    }

    public Event(String description, String date, int id) {
        super(description,
                (String) new ArrayList(Arrays.asList(date.trim().split(" "))).get(0),
                (String) new ArrayList(Arrays.asList(Arrays.asList(date.trim().split(" ")).get(1).split("-"))).get(1),
                id);
        setStartTime((String) new ArrayList(Arrays.asList(Arrays.asList(date.trim().split(" ")).get(1).split("-"))).get(0));
        logger.init("");
        setSavable(description + "/at" + date);
    }

    public void setStartTime(String startTime) {
        if (Objects.equals(startTime, "")) {
            logger.info("empty time");
            return;
        }
        DateFormat formatter = new SimpleDateFormat("HH:mm");
        try {
            this.startTime = formatter.parse(startTime).getTime() + 27000000;
        } catch (ParseException p) {
            logger.info("parsing time got null");
        }
    }

    @Override
    public String toString() {
        String status = isDone ? "X" : " ";
        return id + ". [E][" + status + "] " + super.getDescription() +
                (date == null ? "" : " (at: " + date.format(DateTimeFormatter.ofPattern("MMM d yyyy"))) +
                (startTime == 0 ? "" : " from: " + getStartTime()) +
                (endTime == 0 ? "" : " to: " + getEndTime()) +
                ")";
    }

    @Override
    public String getType() {
        return "event";
    }
}