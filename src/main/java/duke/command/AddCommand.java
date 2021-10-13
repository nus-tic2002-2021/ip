package duke.command;

import duke.exception.DukeException;
import duke.storage.Storage;
import duke.tasklist.*;
import duke.ui.Ui;

import java.io.IOException;

public class AddCommand extends Command {

    Task task;

    public AddCommand(Task task){
        this.task = task;
    }

    public void execute(TaskList taskList, Ui ui, Storage storage) throws IOException {
        taskList.addTask(task);
        storage.setTaskList(taskList.getTaskList());
        ui.showAdded();
        ui.printTask(task.toString());
        ui.printTaskCount(taskList.getListSize());
    }

}
