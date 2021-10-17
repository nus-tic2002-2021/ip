package commands;

import storage.Storage;
import tasks.TaskList;
import ui.Ui;

/**
 * <code>ListCommand</code> is used to list (print out) all the tasks in the TaskList.
 * Extends the <code>Command</code> class.
 */
public class ListCommand extends Command{

    public void execute(TaskList taskList, Ui ui, Storage storage){
        ui.showListed();
        ui.printAllTasks(taskList.getTasks());
    }
}
