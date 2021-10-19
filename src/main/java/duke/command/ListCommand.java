package duke.command;

import duke.tasklist.*;
import duke.storage.*;
import duke.ui.*;

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
