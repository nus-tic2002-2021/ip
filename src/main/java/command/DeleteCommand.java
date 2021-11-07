package command;

import storage.Storage;
import tasklist.TaskList;
import ui.Ui;

import java.io.IOException;

/**
 * An AddCommand object contains a local variable command which specifies the task to be deleted
 */
public class DeleteCommand extends Command{
    String[] command;

    public DeleteCommand(String[] command){
        this.command = command;
    }

    /**
     * The execute method performs delete the task to the task list when called.
     * @param taskList is the task list
     * @param ui to print out message on screen
     * @param storage to write change to task.txt
     * @throws IOException when file not found
     */
    public void execute(TaskList taskList, Ui ui, Storage storage) throws IOException {
        int taskNumber = Integer.parseInt(command[1]) - 1;
        ui.printDelete(taskNumber,taskList);
        taskList.removeTask(taskNumber);
        ui.printTaskCount(taskList);
        storage.writeToFile(taskList.getTasks());
    }
}
