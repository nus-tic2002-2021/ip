package duke.command;

import duke.tasklist.*;
import duke.storage.*;
import duke.ui.*;
import duke.exception.*;

import java.io.IOException;

public class DeleteCommand extends Command {

    private int taskId;

    public DeleteCommand(int taskId){
        this.taskId = taskId;
    }

    public void execute(TaskList taskList, Ui ui, Storage storage) throws DukeException, IOException {
        String deletedTask = taskList.deleteTask(taskId);
        storage.setTaskList(taskList.getTaskList());
        ui.printDeletedTask(deletedTask, taskList.getSize());
    }
}
