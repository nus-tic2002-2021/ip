package duke.command;

import duke.exception.DukeException;
import duke.storage.Storage;
import duke.tasklist.TaskList;
import duke.ui.Ui;

import java.io.IOException;

public abstract class Command {

    protected boolean isExit;

    public Command() {}

    public abstract void execute(TaskList taskList, Ui ui, Storage storage) throws DukeException, IOException;

    public void setExit(boolean exit) {
        isExit = exit;
    }

    public boolean isExit() {
        return isExit;
    }

}
