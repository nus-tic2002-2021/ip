package duke.command;

import duke.tasklist.*;
import duke.storage.*;
import duke.ui.*;

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
