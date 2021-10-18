package Duke.parser;

import Duke.exception.UnknownSyntaxException;
import Duke.storage.Storage;
import Duke.task.Task;
import Duke.task.TaskList;
import Duke.task.ToDos;
import Duke.ui.UI;

public class CMD_TODO extends CMD {
    /**
     * TO DO Command Constructor
     *
     */
    public CMD_TODO(String command) {
        super(command);
    }

    @Override
    public boolean execute(TaskList taskList, UI ui, Storage storage) {
        boolean success = true;
        try {
            if (!CMD_Enum.TODO.getName().equals(super.keyword)) throw new UnknownSyntaxException(super.keyword);
            Task task = new ToDos(super.detail, super.detail);
            taskList.addTask(task);
        } catch (IndexOutOfBoundsException e) {
            success = false;
            throw new UnknownSyntaxException(super.keyword);
        }

        if (success) {
            reply(taskList);
        }

        return false;
    }
}
