package commands;

import exceptions.DukeException;
import storage.Storage;
import tasks.TaskList;
import ui.Ui;
import ui.Messages;
import java.io.IOException;

/**
 * <code>CompleteCommand</code> is used to set the status of a task to "done".
 * Extends the <code>Command</code> class.
 */
public class CompleteCommand extends Command{

    int taskId;

    public CompleteCommand(int taskId){
        this.taskId = taskId;
    }

    public String execute(TaskList taskList, Ui ui, Storage storage) throws DukeException, IOException {
        String completedTask = taskList.setDone(taskId);
        storage.saveTasks(taskList.getTasks());
        ui.showCompleted();
        ui.printTask(completedTask);

        return Messages.COMPLETED_MSG + Messages.getTaskMsg(completedTask);
    }
}
