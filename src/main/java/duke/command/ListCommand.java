package duke.command;

import duke.storage.Storage;
import duke.tasklist.TaskList;
import duke.ui.Ui;

public class ListCommand extends Command{

    public void execute(TaskList taskList, Ui ui, Storage storage){
        ui.printTaskList(taskList.getTaskList());
    }

}
