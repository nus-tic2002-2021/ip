package Duke.parser;

import Duke.storage.Storage;
import Duke.task.TaskList;
import Duke.ui.UI;

public class CMD_Done extends CMD{

    public CMD_Done(String command) {
        super(command);
    }

    @Override
    public boolean execute(TaskList taskList, UI ui, Storage storage) {
        reply(taskList);
        return false;
    }
}