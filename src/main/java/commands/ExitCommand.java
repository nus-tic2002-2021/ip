package commands;

import storage.Storage;
import tasks.TaskList;
import ui.Messages;
import ui.Ui;

/**
 * <code>ExitCommand</code> is used to terminate and exit the Duke program.
 * Extends the <code>Command</code> class.
 */
public class ExitCommand extends Command{

    public String execute(TaskList taskList, Ui ui, Storage storage) {
        ui.showExit();
        super.setExit(true);
        return Messages.EXIT_MSG;
    }
}
