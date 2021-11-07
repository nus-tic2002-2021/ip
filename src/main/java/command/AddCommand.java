package command;

import storage.Storage;
import tasklist.Task;
import tasklist.TaskList;
import ui.Ui;

import java.io.IOException;

/**
 * An AddCommand object contains a local variable task to be added
 */
public class AddCommand extends Command{
    Task task;

    public AddCommand(Task task){
        this.task = task;
    }

    /**
     * The execute method performs add the task to the task list when called.
     * @param taskList is the task list
     * @param ui to print out message on screen
     * @param storage to write change to task.txt
     * @throws IOException when file not found
     */
    public void execute(TaskList taskList, Ui ui, Storage storage) throws IOException {
        taskList.addTask(task);
        storage.writeToFile(taskList.getTasks());
        ui.printAddCommand(taskList);
        ui.printTaskCount(taskList);
    }


}
