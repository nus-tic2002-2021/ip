import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Objects;

public class Event extends Deadline {
    long startTime;

    private static final Logger logger = new Logger();

    public Event(String description, String date, int id) {
        super(description,
                (String) new ArrayList(Arrays.asList(date.trim().split(" "))).get(0),
                (String) new ArrayList(Arrays.asList(Arrays.asList(date.trim().split(" ")).get(1).split("-"))).get(1),
                id);
        setStartTime((String) new ArrayList(Arrays.asList(Arrays.asList(date.trim().split(" ")).get(1).split("-"))).get(0));
        logger.init("");
    }

    public void setStartTime(String startTime) {
        if (Objects.equals(startTime, "")) {
            logger.info("empty time");
            return;
        }
        DateFormat formatter = new SimpleDateFormat("HH:mm");
        try {
            this.startTime = formatter.parse(startTime).getTime();
        } catch (ParseException p) {
            logger.info("parsing time got null");
        }
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