package duke.command;

import duke.tasklist.*;
import duke.storage.*;
import duke.ui.*;
import duke.exception.*;

/**
 * An <code>InvalidCommand</code> object for the execution of any invalid command.
 * Extends the <code>Command</code> class.
 */
public class InvalidCommand extends Command {

    private String message;

    /**
     * Constructs InvalidCommand with this message.
     *
     * @param message The invalid message.
     */
    public InvalidCommand(String message){
        this.message = message;
    }

    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) throws DukeException {
        throw new DukeException(message);
    }

}
