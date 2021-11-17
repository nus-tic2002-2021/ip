package commands;

import exceptions.DukeException;
import storage.Storage;
import tasks.TaskList;
import ui.Messages;
import ui.Ui;
import java.io.IOException;

/**
 * <code>DeleteCommand</code> is used to delete a task from the TaskList.
 * Extends the <code>Command</code> class.
 */
public class DeleteCommand extends Command{

    int taskId;

    public DeleteCommand(int taskId){
        this.taskId = taskId;
    }

    public String execute(TaskList taskList, Ui ui, Storage storage) throws DukeException, IOException {
        String deletedTask = taskList.deleteTask(taskId);
        storage.saveTasks(taskList.getTasks());
        ui.showDeleted();
        ui.printTask(deletedTask);
        ui.printTaskCount(taskList.getListSize());

        return Messages.DELETED_MSG + Messages.getTaskMsg(deletedTask);
    }
}
