package command;

import storage.Storage;
import tasklist.TaskList;
import ui.Ui;

import java.io.IOException;

public class DoneCommand extends Command{
    String[] command;

    public DoneCommand(String[] command){
        this.command = command;
    }

    public void execute(TaskList taskList, Ui ui, Storage storage) throws IOException {
        try {
            int taskNumber = Integer.parseInt(command[1]) - 1;
            taskList.get(taskNumber).setDone(true);
            ui.printDone(taskNumber, taskList);
            ui.printTaskCount(taskList);
            storage.writeToFile(taskList.getTasks());
        } catch (IndexOutOfBoundsException e) {

        }
    }

}
