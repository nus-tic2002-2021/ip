package duke.command;

import duke.storage.Storage;
import duke.tasklist.TaskList;
import duke.ui.Ui;

/**
 * A <code>ByeCommand</code> object for the execution of the bye command.
 * Extends the <code>Command</code> class.
 */
public class ByeCommand extends Command {

    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) {
        ui.showExit();
        super.setExit(true);
    }

}
