package duke.parser;

import duke.exception.TimeManagementException;
import duke.exception.TimeParseException;
import duke.exception.UnknownSyntaxException;
import duke.storage.StorageTaskList;
import duke.task.Events;
import duke.task.Task;
import duke.task.TaskList;
import duke.ui.UI;

import java.time.DateTimeException;
import java.time.LocalDate;
import java.time.LocalTime;

public class CommandEvent extends CommandBase {

    static final int  dateLength = 3;
    /**
     * Creates event Command Constructor
     *
     */
    public CommandEvent(String command)  {
        super(command);
    }


    /**
     * Executes event command.
     *
     * @param taskList user generate.
     * @param ui show interactive information.
     * @param storageTaskList info where to storage.
     * @return if true, the program stops, otherwise it starts.
     * @throws TimeManagementException time management exception if no datetime is given
     */
    @Override
    public boolean execute(TaskList taskList, UI ui, StorageTaskList storageTaskList) throws TimeManagementException{
        boolean success = false;
        try {
            if (!CommandEnums.EVENT.getName().equals(super.keyword)){
                throw new UnknownSyntaxException(super.keyword);
            }
            String[] dt = super.detail.split("/at", 2);
            String description = dt[0];
            String dateTime = dt[1].substring(1);
            LocalDate date = dateParse(dateTime);
            String[] dateTimeInformation = dateTime.split(" ", 2);
            String timeRange = dateTimeInformation[1];
            String from = timeRange.split("-", 2)[0];
            String to = timeRange.split("-", 2)[1];
            Task task = new Events(description, date, timeParse(get24HrFormat(from)),timeParse(get24HrFormat(to)));
            taskList.addTask(task);
            success = true;
        } catch (IndexOutOfBoundsException e) {
            throw new TimeManagementException();
        }

        if (success) {
            reply(taskList);
        }

        return false;
    }

    /**
     * Returns parsed time in format  {DD-MM-YYYY}.
     *
     * @param str - the string of datetime.
     * @return - Date in its correct format.
     * @throws TimeParseException thrown when time format is wrong
     */
    private LocalDate dateParse(String str) throws TimeParseException{
        if (!str.contains("/")){
            throw new TimeParseException("DateTime Format DD/MM/YYYY");
        }
        // gen yyyy,mm,dd ssss
        String[] date = str.split("/", 3);
        if (date.length < dateLength){
            throw new TimeParseException("DateTime Format DD/MM/YYYY");
        }

        // gen time
        String[] time = date[2].split(" ", 2);
        int year = Integer.parseInt(date[0]);
        int month = Integer.parseInt(date[1]);
        int dayOfMth = Integer.parseInt(time[0]);

        LocalDate localDate;
        try {
            localDate = LocalDate.of(year,month,dayOfMth);
        } catch (DateTimeException e) {
            throw new TimeParseException(e.getMessage());
        }

        return localDate;
    }


    /**
     * Transforms time to 24hour format.
     *
     * @param t string such as [1AM] [10PM].
     * @return integer time such as 1, 22.
     */
    private int get24HrFormat(String t){
        int timeinterval = 12;
        int timeVal;
        if(t.contains("AM")){
            timeVal = Integer.parseInt(t.replace("AM", "").stripTrailing());
        } else{
            timeVal = Integer.parseInt(t.replace("PM", "").stripTrailing());
            timeVal +=timeinterval;
        }

        return timeVal;
    }

    /**
     * Returns time in LocalTime format.
     *
     * @param t the integer time [1] [22]
     * @return time in LocalTime [01:00:00] [22:00:00]
     */
    private LocalTime timeParse(int t){
        return LocalTime.of(t,0,0);
    }
}