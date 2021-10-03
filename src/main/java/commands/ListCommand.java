package commands;

import storage.Storage;
import tasks.TaskList;
import ui.Ui;

public class ListCommand extends Command{

    public void execute(TaskList taskList, Ui ui, Storage storage){
        ui.printAllTasks(taskList.getTasks());
    }
}
