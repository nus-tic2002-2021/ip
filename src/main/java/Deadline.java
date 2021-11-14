import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Objects;
import java.util.concurrent.TimeUnit;

public class Deadline extends Todo {
    protected LocalDate date;
    protected long endTime;

    String savable;
    Parser parser = new Parser();
    Logger logger = new Logger();

    public Deadline(String description, String endDateTime, int id) {
        super(description, id);

        logger.init("");
        logger.info("get description: " + description);
        logger.info("get endDateTime: " + endDateTime.trim());
        ArrayList<String> tokens = new ArrayList(Arrays.asList(endDateTime.trim().split(" ")));
        logger.info("set tokens: " + tokens);
        setDate(tokens.get(0));
        setEndTime(tokens.get(1));
        setSavable(description + "/by" + endDateTime);
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

    public Deadline(String description, String date, String endTime, int id) {
        super(description, id);
        setDate(date);
        setEndTime(endTime);
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(String date) {
        logger.info("set date: " + date);
        if (Objects.equals(date, "")) {
            return;
        }
        LocalDate parsedDate = parser.stringToDate(date);
        if (parsedDate == null) {
            logger.info("parsing date got null");
        } else {
            this.date = parsedDate;
        }
    }

    public void setEndTime(String time) {
        logger.info("set end_time: " + time);
        DateFormat formatter = new SimpleDateFormat("HH:mm");
        try {
            this.endTime = formatter.parse(time).getTime() + 27000000;
        } catch (ParseException p) {
            logger.info("parsing time got exception" + p.getMessage());
        }
    }

    public String getEndTime() {
        return String.format("%02d:%02d",
                TimeUnit.MILLISECONDS.toHours(endTime),
                TimeUnit.MILLISECONDS.toMinutes(endTime) -
                        TimeUnit.HOURS.toMinutes(TimeUnit.MILLISECONDS.toHours(endTime)));

    }

    public String getSavable() {
        return savable;
    }

    public void setSavable(String savable) {
        this.savable = savable;
    }

    @Override
    public String toTask() {
        return getType() + "," + getSavable() + "," + (isDone ? "1" : "0");
    }

    @Override
    public String toString() {
        String status = isDone ? "X" : " ";
        return id + ". [D][" + status + "] " + super.getDescription().trim() +
                (date == null ? "" : " (at: " + date.format(DateTimeFormatter.ofPattern("MMM d yyyy"))) +
                (endTime == 0 ? "" : " by: " + getEndTime()) +
                ")";

    }

    @Override
    public String getType() {
        return "deadline";
    }
}