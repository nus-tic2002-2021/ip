package duke.command;

import duke.tasklist.*;
import duke.storage.*;
import duke.ui.*;

import java.io.IOException;

/**
 * An <code>AddCommand</code> object for the execution of the add command.
 * Extends the <code>Command</code> class.
 */
public class AddCommand extends Command {

    private Task task;

    /**
     * Constructs AddCommand with this task.
     *
     * @param task The task to be added.
     */
    public AddCommand(Task task){
        this.task = task;
    }

    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) throws IOException {
        taskList.addTask(task);
        storage.setTaskList(taskList.getTaskList());
        ui.printAddedTask(task.toString(), taskList.getSize());
    }

}
