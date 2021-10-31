package commands;

import storage.Storage;
import tasks.TaskList;
import ui.Messages;
import ui.Ui;

/**
 * <code>ListCommand</code> is used to list (print out) all the tasks in the TaskList.
 * Extends the <code>Command</code> class.
 */
public class HelpCommand extends Command{

    public String execute(TaskList taskList, Ui ui, Storage storage) {
        ui.showHelp();
        return Messages.HELP_MSG;
    }
}
