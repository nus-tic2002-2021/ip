package commands;

import exceptions.DukeException;
import storage.Storage;
import tasks.*;
import tasks.TaskList;
import ui.Ui;

import java.io.IOException;

public class AddCommand extends Command{
    Task task;

    public AddCommand(Task task){
        this.task = task;
    }

    public void execute(TaskList taskList, Ui ui, Storage storage) throws IOException {
        taskList.addTask(task);
        storage.saveTasks(taskList.getTasks());
        ui.showAdded();
        ui.printTask(task.toString());
        ui.printTaskCount(taskList.getListSize());
    }
}
