package duke.command;

import duke.tasklist.*;
import duke.storage.*;
import duke.ui.*;
import duke.exception.*;

import java.io.IOException;

/**
 * A <code>DeleteCommand</code> object for the execution of the delete command.
 * Extends the <code>Command</code> class.
 */
public class DeleteCommand extends Command {

    private int taskId;

    /**
     * Constructs DeleteCommand with this task id.
     *
     * @param taskId The task id to be deleted.
     */
    public DeleteCommand(int taskId){
        this.taskId = taskId;
    }

    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) throws DukeException, IOException {
        String deletedTask = taskList.deleteTask(taskId);
        storage.setTaskList(taskList.getTaskList());
        ui.printDeletedTask(deletedTask, taskList.getSize());
    }
}
