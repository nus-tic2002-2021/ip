package command;

import storage.Storage;
import tasklist.TaskList;
import ui.Ui;

import java.io.IOException;

/**
 * An ListCommand object list down all the tasks in the list
 */
public class ListCommand extends Command {

    /**
     * The execute method perform listing all tasks in the list
     * @param taskList is the task list
     * @param ui to print out message on screen
     * @param storage not used here
     * @throws IOException when file not found
     */
    public void execute(TaskList taskList, Ui ui, Storage storage) {
        ui.printTaskList(taskList);
        ui.printTaskListStr(taskList);
    }
}
