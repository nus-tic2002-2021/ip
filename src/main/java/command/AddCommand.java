package command;

import storage.Storage;
import tasklist.Task;
import tasklist.TaskList;
import ui.Ui;

import java.io.IOException;

public class AddCommand extends Command{
    Task task;

    public AddCommand(Task task){
        this.task = task;
    }

    public void execute(TaskList taskList, Ui ui, Storage storage) throws IOException {
        taskList.addTask(task);
        storage.writeToFile(taskList.getTasks());
        ui.printAddCommand(taskList);
        ui.printTaskCount(taskList);
    }


}
