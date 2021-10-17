package Duke.parser;

import Duke.exception.EmptyDescriptionException;
import Duke.exception.UnknownSyntaxException;
import Duke.storage.Storage;
import Duke.task.TaskList;
import Duke.ui.UI;

public class CMD_Delete extends CMD{
    public CMD_Delete(String command) {
        super(command);
    }

    @Override
    public boolean execute(TaskList taskList, UI ui, Storage storage) {
        taskList.deleteTask(Integer.parseInt(super.fullCommand.split(" ", 2)[1]));
        return false;
    }
}
