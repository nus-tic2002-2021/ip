package duke.command;

import duke.storage.Storage;
import duke.tasklist.TaskList;
import duke.ui.Ui;

/**
 * A <code>ListCommand</code> object for the execution of the list command.
 * Extends the <code>Command</code> class.
 */
public class ListCommand extends Command {

    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) {
        ui.printTaskList(taskList.getTaskList());
    }

}
