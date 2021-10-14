package duke.command;

import duke.tasklist.*;
import duke.storage.*;
import duke.ui.*;

public class ListCommand extends Command {

    public void execute(TaskList taskList, Ui ui, Storage storage) {
        ui.printTaskList(taskList.getTaskList());
    }

}
