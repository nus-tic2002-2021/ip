package Duke.parser;

import Duke.exception.TimeManagementException;
import Duke.exception.UnknownSyntaxException;
import Duke.storage.Storage;
import Duke.task.Events;
import Duke.task.Task;
import Duke.task.TaskList;
import Duke.ui.UI;

public class CMD_Event extends CMD{
    public CMD_Event(String command)  {
        super(command);
    }

    @Override
    public boolean execute(TaskList taskList, UI ui, Storage storage) {
        boolean success = true;
        try {
            if (!CMD_Enum.EVENT.getName().equals(super.keyword)) throw new UnknownSyntaxException(super.keyword);
            String[] dt = detail.split("/at", 2);
            Task task = new Events(dt[0], dt[1]);
            taskList.addTask(task);
        } catch (IndexOutOfBoundsException e) {
            success = false;
            throw new TimeManagementException();
        }

        if (success) {
            reply(taskList);
        }

        return false;
    }
}