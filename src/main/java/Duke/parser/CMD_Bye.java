package Duke.parser;

import Duke.storage.Storage;
import Duke.task.TaskList;
import Duke.ui.UI;

public class CMD_Bye extends CMD {
    /**
     * Bye Command Constructor
     *
     */
    public CMD_Bye(String command){
        super(command);
    }

    /**
     * Function Overloading for execution of bye command
     *
     * @param taskList the full task list for saving purpose
     * @param ui       UI object
     * @param storage  storage object
     *
     */
    @Override
    public boolean execute(TaskList taskList, UI ui, Storage storage) {
        storage.save(taskList);
        return true;
    }
}
