package duke.parser;

import duke.exception.EmptyTaskListException;
import duke.exception.TimeParseException;
import duke.exception.UnknownSyntaxException;
import duke.storage.StorageTaskList;
import duke.task.TaskList;
import duke.ui.UI;

import java.time.DateTimeException;
import java.time.LocalDate;

public class CommandView extends CommandBase{

    static final int  dateLength = 3;

    /**
     * Creates view schedule command constructor.
     *
     * @param command the full command.
     */
    public CommandView(String command){super(command);}


    /**
     * Executes view schedule command
     *
     * @param taskList the full task list for sorting purpose.
     * @param ui show interactive information.
     * @param storageTaskList storage object.
     * @return returns false as this command does not end the runtime
     * @throws EmptyTaskListException when task list is empty no schedule to sort
     */
    @Override
    public boolean execute(TaskList taskList, UI ui, StorageTaskList storageTaskList) throws EmptyTaskListException{
        try{
            if (!CommandEnums.VIEW.getName().equals(super.keyword)){
                throw new UnknownSyntaxException(super.keyword);
            }
            LocalDate scheduleDate = dateParse(super.detail);
            taskList.printAsSchedule(scheduleDate);
        } catch(EmptyTaskListException e){
            throw new EmptyTaskListException("Empty");
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
            throw new TimeParseException("DateTime Format YYYY/MM/DD");
        }
        // gen yyyy,mm,dd ssss
        String[] date = str.split("/", 3);
        if (date.length < dateLength){
            throw new TimeParseException("DateTime Format YYYY/MM/DD");
        }

        int year = Integer.parseInt(date[0]);
        int month = Integer.parseInt(date[1]);
        int dayOfMth = Integer.parseInt(date[2]);

        LocalDate localDate;
        try {
            localDate = LocalDate.of(year,month,dayOfMth);
        } catch (DateTimeException e) {
            throw new TimeParseException(e.getMessage());
        }

        return localDate;
    }
}
