package Duke.parser;

import Duke.storage.Storage;
import Duke.task.TaskList;
import Duke.ui.UI;

public class CMD_Bye extends CMD {
    public CMD_Bye(String command){
        super(command);
    }

    @Override
    public boolean execute(TaskList taskList, UI ui, Storage storage) {
        storage.save(taskList);
        return true;
    }
}
