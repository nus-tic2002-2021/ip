package tasklist;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;

/**
 * An Deadline object holds deadline type of task which contains LocalDateTime variable
 *
 */
public class Deadline extends Task {
    protected LocalDateTime dateTime;

    public Deadline(String description, LocalDateTime dateTime){
        super(description);
        this.type = "D";
        this.isDone = false;
        this.dateTime = dateTime;
    }

    @Override
    public String getDescription() {return description.substring(9,description.indexOf('/'));}

    public  String getDateTimeStr() {
        return dateTime.format(DateTimeFormatter.ofLocalizedDateTime(FormatStyle.MEDIUM, FormatStyle.SHORT));
    }

    public LocalDateTime getDateTime(){return dateTime;}

    public String printTask (){
        return "[" + getType() +"]" + "[" + getStatusIcon() +"] " + getDescription() + "(by: " + getDateTimeStr() + ")";
    }
    public String[] getKeyword (){
        return description.split("/by");
    }

}
