package duke.command;

import duke.exception.DukeException;
import duke.storage.Storage;
import duke.tasklist.TaskList;
import duke.ui.Ui;

import java.io.IOException;

public class DeleteCommand extends Command{

    int taskId;

    public DeleteCommand(int taskId){
        this.taskId = taskId;
    }

    public void execute(TaskList taskList, Ui ui, Storage storage) throws DukeException, IOException {
        String deletedTask = taskList.deleteTask(taskId);
        storage.setTaskList(taskList.getTaskList());
        ui.showDeleted();
        ui.printTask(deletedTask);
        ui.printTaskCount(taskList.getListSize());
    }
}
