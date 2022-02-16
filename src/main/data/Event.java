package data;

import parser.Parser;

import java.time.DateTimeException;
import java.time.LocalDateTime;

public class Event extends Task{
    private String clock;
    private LocalDateTime atwhen;
    private String dateTimeForStorage;

    public Event(String des, Acronym acro, String hour) {
        super(des, acro);
        dateTimeForStorage = hour;
        atwhen = Parser.parseStringDateTimetoLocaLDateTime(hour);
        clock = Parser.parseDateForDisplay(atwhen);

    }

    @Override
    public String displayDateTime(){
        return clock;
    }

    public String getDateTimeForStorage()
    {
        return dateTimeForStorage;
    }

    public LocalDateTime getAtwhen(){
        return atwhen;
    }
}
