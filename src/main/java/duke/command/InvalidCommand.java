package duke.command;

import duke.exception.DukeException;
import duke.storage.Storage;
import duke.tasklist.TaskList;
import duke.ui.Ui;

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
