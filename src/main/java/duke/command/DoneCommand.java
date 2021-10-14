package duke.command;

import duke.tasklist.*;
import duke.storage.*;
import duke.ui.*;
import duke.exception.*;

import java.io.IOException;

public class DoneCommand extends Command {

    private int taskId;

    public DoneCommand(int taskId){
        this.taskId = taskId;
    }

    public void execute(TaskList taskList, Ui ui, Storage storage) throws DukeException, IOException {
        String completedTask = taskList.setDone(taskId);
        storage.setTaskList(taskList.getTaskList());
        ui.printCompletedTask(completedTask);
    }

}
