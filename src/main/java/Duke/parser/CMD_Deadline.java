package Duke.parser;

import Duke.exception.TimeManagementException;
import Duke.exception.TimeParseException;
import Duke.exception.UnknownSyntaxException;
import Duke.storage.Storage;
import Duke.task.Deadline;
import Duke.task.Task;
import Duke.task.TaskList;
import Duke.ui.UI;

import java.time.DateTimeException;
import java.time.LocalDateTime;


public class
CMD_Deadline extends CMD{
    /**
     * Deadline Command Constructor
     *
     */
    public CMD_Deadline(String command){
        super(command);
    }


    /**
     * Function Overloading for execution of deadline command
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
            if (!CMD_Enum.DEADLINE.getName().equals(super.keyword)) throw new UnknownSyntaxException(super.keyword);
            String[] dl = super.detail.split("/by", 2);
            //region time parse
            String[] s = dl[1].split(" ", 2);
            LocalDateTime dateT = timeParse(s[1]);
            //endregion
            Task task = new Deadline(dl[0], dateT);
            taskList.addTask(task);
        } catch (IndexOutOfBoundsException e) {
            success = false;
            throw new TimeManagementException();
        } catch (TimeParseException e) {
            success = false;
            throw new TimeParseException(e.getMessage());
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
    private LocalDateTime timeParse(String str) throws TimeParseException{
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
        int hour = get24HrFormat(time[1]);

        LocalDateTime localDate;
        try {
            //LocalDateTime.of(,,);
            localDate = LocalDateTime.of(year,month,dayOfMth,hour,0,0,0);
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
}
