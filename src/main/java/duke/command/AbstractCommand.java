package duke.command;

import duke.app.TaskList;
import duke.app.Ui;
import duke.storage.Storage;

public abstract class AbstractCommand {

    public abstract void execute(TaskList taskList, Storage storage, Ui ui);

    public boolean isExit() {return false;}
}
