package tasks;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;

public class EventTask extends Task {

    protected LocalDateTime at;

    public EventTask(String description, LocalDateTime at) {
        super(description);
        this.at = at;
    }
    public String getAt() { return at.format(DateTimeFormatter.ofLocalizedDateTime(FormatStyle.MEDIUM)); }

    @Override
    public String toString()
    {
        return "[E]" + super.toString() + " (at: " + getAt() + ")";
    }
}
