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

    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) throws DukeException, IOException {
        taskList.reset();
        ui.printReset();
        ui.printTaskCount(taskList);
        storage.writeToFile(taskList.getTasks());
    }
}
