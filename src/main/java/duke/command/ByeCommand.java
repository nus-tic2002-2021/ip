package duke.command;

import duke.tasklist.*;
import duke.storage.*;
import duke.ui.*;

public class ByeCommand extends Command {

    public void execute(TaskList taskList, Ui ui, Storage storage){
        ui.showExit();
        super.setExit(true);
    }

}
