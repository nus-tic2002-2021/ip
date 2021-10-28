package duke.parser;

import duke.exception.TimeManagementException;
import duke.exception.TimeParseException;
import duke.exception.UnknownSyntaxException;
import duke.storage.StorageTaskList;
import duke.task.Deadline;
import duke.task.Task;
import duke.task.TaskList;
import duke.ui.UI;

import java.time.DateTimeException;
import java.time.LocalDateTime;


public class
CommandDeadline extends CommandBase {

    static final int  dateLength = 3;
    /**
     * Creates deadline constructor.
     *
     * @param command full command.
     */
    public CommandDeadline(String command){
        super(command);
    }


    /**
     * Executes deadline command.
     *
     * @param taskList user generate.
     * @param ui show interactive information.
     * @param storageTaskList info where to storage.
     * @return if true, the program stops, otherwise it starts.
     * @throws TimeManagementException thrown when datetime not specified
     * @throws TimeParseException thrown when datetime format is wrong
     */
    @Override
    public boolean execute(TaskList taskList, UI ui, StorageTaskList storageTaskList) throws TimeManagementException, TimeParseException{
        boolean success = false;
        try {
            if (!CommandEnums.DEADLINE.getName().equals(super.keyword)){
                throw new UnknownSyntaxException(super.keyword);
            }
            String[] deadlineCommand = super.detail.split("/by", 2);
            //region time parse
            String[] dateTimes = deadlineCommand[1].split(" ", 2);
            LocalDateTime dateT = timeParse(dateTimes[1]);
            //endregion
            Task task = new Deadline(deadlineCommand[0], dateT);
            taskList.addTask(task);
            success = true;
        } catch (IndexOutOfBoundsException e) {
            throw new TimeManagementException();
        } catch (TimeParseException e) {
            throw new TimeParseException(e.getMessage());
        }

        if (success) {
            reply(taskList);
        }

        return false;
    }

    /**
     * Returns parsed time as format {DD-MM-YYYY}.
     *
     * @param str - the string of datetime.
     * @return - Date in its correct format.
     * @throws TimeParseException thrown when date format is wrong
     */
    private LocalDateTime timeParse(String str) throws TimeParseException{
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
        int hour = get24HrFormat(time[1]);

        LocalDateTime localDate;
        try {
            localDate = LocalDateTime.of(year,month,dayOfMth,hour,0,0,0);
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
        int timeInterval = 12;
        int timeVal;
        if(t.contains("AM")){
            timeVal = Integer.parseInt(t.replace("AM", "").stripTrailing());
        } else{
            timeVal = Integer.parseInt(t.replace("PM", "").stripTrailing());
            timeVal +=timeInterval;
        }
        return timeVal;
    }
}
