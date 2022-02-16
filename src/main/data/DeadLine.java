package data;

import parser.Parser;

import java.time.DateTimeException;
import java.time.LocalDateTime;

public class DeadLine extends Task{
    protected String date;
    private LocalDateTime bywhen;
    private String dateTimeForStorage;

    /**
     * deadline class constructor mainly called from ListTask class for adding deadline Task
     * @param des Description of the task
     * @param acro Acronym of the Task, in this case D out of (T,E,D)
     * @param dat TimeStamp of the task
     */
    public DeadLine(String des, Acronym acro, String dat){
        super(des,acro);
        bywhen = Parser.parseStringDateTimetoLocaLDateTime(dat);
        date = Parser.parseDateForDisplay(bywhen);
        dateTimeForStorage = Parser.parseDateForStorage(bywhen);

    }

    @Override
    public String displayDateTime(){
        return date;
    }

    public String getDateTimeForStorage()
    {
        return dateTimeForStorage;
    }

    public LocalDateTime getbywhen(){
        return bywhen;
    }

}
