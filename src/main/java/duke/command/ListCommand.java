package duke.command;

import duke.app.TaskList;
import duke.app.Ui;
import duke.storage.Storage;

/**
 * Print all the tasks in the list
 */
public class ListCommand extends AbstractCommand{
    @Override
    public void execute(TaskList taskList, Storage storage, Ui ui) {
        ui.printTask();
        taskList.printTask(taskList.getTaskList());
    }
}

