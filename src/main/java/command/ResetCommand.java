package command;

import exception.DukeException;
import storage.Storage;
import tasklist.TaskList;
import ui.Ui;

import java.io.IOException;

public class ResetCommand extends Command{

    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) throws DukeException, IOException {
        taskList.reset();
        ui.reset();
        ui.printTaskCount(taskList);
        storage.saveTask(taskList.getTasks());
    }
}
