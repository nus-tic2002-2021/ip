package duke.command;

import duke.tasklist.*;
import duke.storage.*;
import duke.ui.*;
import duke.exception.*;

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
