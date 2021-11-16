package duke.command;

import duke.app.TaskList;
import duke.app.Ui;
import duke.storage.Storage;
import duke.task.Task;

import java.util.List;

/**
 * print all the tasks before a user-specified date
 */
public class ReminderCommand extends AbstractCommand{

    @Override
    public void execute(TaskList taskList, Storage storage, Ui ui) {
        List<Task> criticalTasks = taskList.getTasksReminder();
        ui.showRemindCommandMessage();
        taskList.printTask(criticalTasks);
    }
}
