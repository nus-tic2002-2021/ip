package Duke.parser;

import Duke.exception.TimeManagementException;
import Duke.exception.TimeParseException;
import Duke.exception.UnknownSyntaxException;
import Duke.storage.Storage;
import Duke.task.Events;
import Duke.task.Task;
import Duke.task.TaskList;
import Duke.ui.UI;

import java.time.DateTimeException;
import java.time.LocalDate;
import java.time.LocalTime;

public class CMD_Event extends CMD{
    /**
     * Event Command Constructor
     *
     */
    public CMD_Event(String command)  {
        super(command);
    }


    /**
     * Function Overloading for execution of event command
     *
     * @param taskList the full task list for saving purpose
     * @param ui       UI object
     * @param storage  storage object
     *
     */
    @Override
    public boolean execute(TaskList taskList, UI ui, Storage storage) {
        boolean success = true;
        try {
            if (!CMD_Enum.EVENT.getName().equals(super.keyword)) throw new UnknownSyntaxException(super.keyword);
            String[] dt = detail.split("/at", 2);
            String desc = dt[0];
            String dateTime = dt[1].substring(1);
            LocalDate date = dateParse(dateTime);
            String[] dayTimeInfo = dateTime.split(" ", 2);
            String timeRange = dayTimeInfo[1];
            String from = timeRange.split("-", 2)[0];
            String to = timeRange.split("-", 2)[1];
            Task task = new Events(dt[0], date, timeParse(get24HrFormat(from)),timeParse(get24HrFormat(to)));
            taskList.addTask(task);
        } catch (IndexOutOfBoundsException e) {
            success = false;
            throw new TimeManagementException();
        }

        if (success) {
            reply(taskList);
        }

        return false;
    }

    /**
     * Help parse the time to yyyy,mm,dd ssss
     * @param str - the string of datetime
     * @return - datetime in its correct format
     */
    private LocalDate dateParse(String str) throws TimeParseException{
        if (!str.contains("/")) throw new TimeParseException("DateTime Format DD/MM/YYYY");
        // gen yyyy,mm,dd ssss
        String[] date = str.split("/", 3);
        if (date.length < 3) throw new TimeParseException("DateTime Format DD/MM/YYYY");

        // gen time
        System.out.println("date2: "+date[2]);
        String[] time = date[2].split(" ", 2);
        int year = Integer.parseInt(date[0]);
        int month = Integer.parseInt(date[1]);
        int dayOfMth = Integer.parseInt(time[0]);

        LocalDate localDate;
        try {
            localDate = LocalDate.of(year,month,dayOfMth);
            System.out.println(localDate);
        } catch (DateTimeException e) {
            throw new TimeParseException(e.getMessage());
        }

        return localDate;
    }


    /**
     * Transforms time to 24hour format
     * @param t string such as [1AM] [10PM]
     * @return integer time such as 1, 22
     */
    private int get24HrFormat(String t){
        int timeVal;
        if(t.contains("AM")){
            timeVal = Integer.parseInt(t.replace("AM", "").stripTrailing());
        }
        else{
            timeVal = Integer.parseInt(t.replace("PM", "").stripTrailing());
            timeVal +=12;
        }
        return timeVal;
    }

    /**
     * converts time in integer to LocalTime
     * @param t the integer time [1] [22]
     * @return time in LocalTime [01:00:00] [22:00:00]
     */
    private LocalTime timeParse(int t){
        return LocalTime.of(t,0,0);
    }
}