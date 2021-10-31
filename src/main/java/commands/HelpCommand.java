package commands;

import storage.Storage;
import tasks.TaskList;
import ui.Messages;
import ui.Ui;

/**
 * <code>HelpCommand</code> is used to list all command types and their formats.
 * Extends the <code>Command</code> class.
 */
public class HelpCommand extends Command{

    public String execute(TaskList taskList, Ui ui, Storage storage) {
        ui.showHelp();
        return Messages.HELP_MSG;
    }
}
