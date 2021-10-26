package Duke.parser;

import Duke.exception.TimeManagementException;
import Duke.exception.UnknownSyntaxException;
import Duke.storage.Storage;
import Duke.task.Events;
import Duke.task.Task;
import Duke.task.TaskList;
import Duke.ui.UI;

import java.time.LocalDate;

public class CMD_Find extends CMD{
    /**
     * Find Command Constructor
     *
     */
    public CMD_Find(String command) {
        super(command);
    }

    /**
     * Function Overloading for execution of Done command
     *
     * @param taskList the full task list for saving purpose
     * @param ui       UI object
     * @param storage  storage object
     *
     */
    @Override
    public boolean execute(TaskList taskList, UI ui, Storage storage) {
        try{
            System.out.println("detail: " +super.detail);
            taskList.printFoundTaskList(super.detail);
        }
        catch(Exception e){
            System.out.println(e.getMessage());
        }
        return false;
    }
}
