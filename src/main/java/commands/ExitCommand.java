package commands;

import storage.Storage;
import tasks.TaskList;
import ui.Ui;

/**
 * <code>ExitCommand</code> is used to terminate and exit the Duke program.
 * Extends the <code>Command</code> class.
 */
public class ExitCommand extends Command{

    public void execute(TaskList taskList, Ui ui, Storage storage){
        ui.showExit();
        super.setExit(true);
    }
}
