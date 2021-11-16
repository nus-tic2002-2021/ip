package duke.command;

import duke.app.TaskList;
import duke.app.Ui;
import duke.storage.Storage;
import duke.task.Task;
import java.util.List;

public class FindComment extends AbstractCommand{
    @Override
    public void execute(TaskList taskList, Storage storage, Ui ui) {
        ui.printFindCommand();
        List<Task> matchTasks = taskList.findMatchTasks();
        if (!matchTasks.isEmpty()) {
            taskList.printTask(matchTasks);
        }
    }
}
