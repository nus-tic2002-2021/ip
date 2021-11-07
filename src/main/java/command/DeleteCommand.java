package command;

import storage.Storage;
import tasklist.TaskList;
import ui.Ui;

import java.io.IOException;

public class DeleteCommand extends Command{
    String[] command;

    public DeleteCommand(String[] command){
        this.command = command;
    }

    public void execute(TaskList taskList, Ui ui, Storage storage) throws IOException {
        int taskNumber = Integer.parseInt(command[1]) - 1;
        ui.printDelete(taskNumber,taskList);
        taskList.removeTask(taskNumber);
        ui.printTaskCount(taskList);
        storage.writeToFile(taskList.getTasks());
    }
}
