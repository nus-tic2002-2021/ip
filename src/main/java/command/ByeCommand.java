package command;

import storage.Storage;
import tasklist.TaskList;
import ui.Ui;

public class ByeCommand extends Command{
    public void execute(TaskList taskList, Ui ui, Storage storage) {
        ui.goodBye();
        super.setExit(true);
    }
}
