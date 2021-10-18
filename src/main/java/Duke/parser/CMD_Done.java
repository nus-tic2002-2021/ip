package Duke.parser;

import Duke.storage.Storage;
import Duke.task.TaskList;
import Duke.ui.UI;

public class CMD_Done extends CMD{
    /**
     * Done Command Constructor
     *
     */
    public CMD_Done(String command) {
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
        reply(taskList);
        return false;
    }
}