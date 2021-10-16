package duke.task;

import duke.DateFun;

import java.time.LocalDate;

public class Event extends Task{
    String at;
    LocalDate begin;
    LocalDate end;

    public Event(String description, String at){
        super(description);
        this.at = at;
        String[] dateStr = at.split(" - ");
        begin = DateFun.stringToLocalDate(dateStr[0]);
        end = DateFun.stringToLocalDate(dateStr[1]);
        type = Types.EVENT;
    }

    public Event(boolean isDone, String description, String at){
        super(description);
        this.at = at;
        this.isDone = isDone;
        String[] dateStr = at.split(" - ");
        begin = DateFun.stringToLocalDate(dateStr[0]);
        end = DateFun.stringToLocalDate(dateStr[1]);
        type = Types.EVENT;
    }

    public void showTask(){
        String mark = isDone ? "X" : " ";
        System.out.println("[E][" + mark + "] " + description + " (at: " + DateFun.LocalDateToString(begin) + " - " + DateFun.LocalDateToString(end) + ")");
    }

    public String toStoreString(){
        int state = isDone ? 1 : 0;
        return 'E' + " | " + state + " | " + description + " | " + at + System.lineSeparator();
    }
}