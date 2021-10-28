package duke.command;

import duke.exception.DukeException;
import duke.storage.Storage;
import duke.tasklist.TaskList;
import duke.ui.Ui;

import java.io.IOException;

/**
 * A <code>DoneCommand</code> object for the execution of the done command.
 * Extends the <code>Command</code> class.
 */
public class DoneCommand extends Command {

    private int taskId;

    /**
     * Constructs DoneCommand with this task id.
     *
     * @param taskId The task id to be marked as done.
     */
    public DoneCommand(int taskId) {
        this.taskId = taskId;
    }

    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) throws DukeException, IOException {
        String completedTask = taskList.setDone(taskId);
        storage.setTaskList(taskList.getTaskList());
        ui.printCompletedTask(completedTask);
    }

}
