package Duke.parser;

import Duke.storage.Storage;
import Duke.task.TaskList;
import Duke.ui.UI;

public class CMD_Delete extends CMD{
    /**
     * Delete Command Constructor
     *
     */
    public CMD_Delete(String command) {
        super(command);
    }

    /**
     * Function Overloading for execution of delete command
     *
     * @param taskList the full task list for saving purpose
     * @param ui       UI object
     * @param storage  storage object
     *
     */
    @Override
    public boolean execute(TaskList taskList, UI ui, Storage storage) {
        taskList.deleteTask(Integer.parseInt(super.fullCommand.split(" ", 2)[1]));
        return false;
    }
}
