package command;

import exception.DukeException;
import storage.Storage;
import tasklist.TaskList;
import ui.Ui;

import java.io.IOException;

/**
 * The ResetCommand object clears all task in list
 * and prints out the current task count
 * and writes the change to tasks.txt file
 */
public class ResetCommand extends Command{

    /**
     * The execute method perform deleting all tasks in the list
     * @param taskList is the task list
     * @param ui to print out message on screen
     * @param storage to write change to task.txt
     * @throws IOException when file not found
     */
    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) throws DukeException, IOException {
        if (ui.validateResetCommand()) {
            taskList.reset();
            ui.printReset();
            ui.printTaskCount(taskList);
            storage.writeToFile(taskList.getTasks());
        } else {
            ui.printNotReset();
        }
    }
}
