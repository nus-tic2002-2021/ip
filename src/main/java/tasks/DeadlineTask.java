package tasks;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;

public class DeadlineTask extends Task {

    protected LocalDate by;

    public DeadlineTask(String description, LocalDate by) {
        super(description);
        this.by = by;
    }

    public String getBy(){ return by.format(DateTimeFormatter.ofLocalizedDate(FormatStyle.MEDIUM)); }

    @Override
    public String toString(){ return "[D]" + super.toString() + " (by: " + getBy() + ")"; }
}
