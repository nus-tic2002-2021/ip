package duke.command;

import duke.exception.DukeException;
import duke.storage.Storage;
import duke.tasklist.TaskList;
import duke.ui.Ui;

import java.io.IOException;

public class DoneCommand extends Command {

    int taskId;

    public DoneCommand(int taskId){
        this.taskId = taskId;
    }

    public void execute(TaskList taskList, Ui ui, Storage storage) throws DukeException, IOException {
        String completedTask = taskList.setDone(taskId);
        storage.setTaskList(taskList.getTaskList());
        ui.showCompleted();
        ui.printTask(completedTask);
    }

}
