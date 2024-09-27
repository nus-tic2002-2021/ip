package command;

import storage.Storage;
import tasklist.TaskList;
import ui.Ui;

import java.io.IOException;

/**
 * An DoneCommand object contains a local variable command which specifies the task to be set as done
 */
public class DoneCommand extends Command{
    String[] command;

    public DoneCommand(String[] command){
        this.command = command;
    }

    /**
     * The execute method performs setting task status to done.
     * @param taskList is the task list
     * @param ui to print out message on screen
     * @param storage to write change to task.txt
     * @throws IOException when file not found
     */
    public void execute(TaskList taskList, Ui ui, Storage storage) throws IOException {
        int taskNumber = Integer.parseInt(command[1]) - 1;
        taskList.get(taskNumber).setDone(true);
        ui.printDone(taskNumber, taskList);
        ui.printTaskCount(taskList);
        storage.writeToFile(taskList.getTasks());
    }

}
