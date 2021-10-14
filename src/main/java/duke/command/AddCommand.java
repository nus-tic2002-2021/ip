package duke.command;

import duke.tasklist.*;
import duke.storage.*;
import duke.ui.*;

import java.io.IOException;

public class AddCommand extends Command {

    private Task task;

    public AddCommand(Task task){
        this.task = task;
    }

    public void execute(TaskList taskList, Ui ui, Storage storage) throws IOException {
        taskList.addTask(task);
        storage.setTaskList(taskList.getTaskList());
        ui.printAddedTask(task.toString(), taskList.getSize());
    }

}
