package duke.command;

import duke.exception.DukeException;
import duke.storage.Storage;
import duke.tasklist.TaskList;
import duke.ui.Ui;

import java.io.IOException;

/**
 * An abstract <code>Command</code> class to be extended by other command classes.
 */
public abstract class Command {

    private boolean isExit;

    public Command() {}

    public void execute(TaskList taskList, Ui ui, Storage storage) throws DukeException, IOException {}

    public boolean isExit() {
        return isExit;
    }

    public void setExit(boolean exit) {
        isExit = exit;
    }

}
