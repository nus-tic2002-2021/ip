package Duke.parser;

import Duke.storage.Storage;
import Duke.task.TaskList;
import Duke.ui.UI;

public class CMD_List extends CMD {
    /**
     * List Command Constructor
     *
     */
    public CMD_List(String command) {
        super(command);
    }

    /**
     * Function Overloading for execution of list command
     *
     * @param taskList the full task list for saving purpose
     * @param ui       UI object
     * @param storage  storage object
     *
     */
    @Override
    public boolean execute(TaskList taskList, UI ui, Storage storage) {
        taskList.printTaskList();
        return false;
    }
}