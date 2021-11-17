package commands;

import exceptions.DukeException;
import storage.Storage;
import tasks.TaskList;
import ui.Ui;

/**
 * <code>InvalidCommand</code> is used to capture invalid input command that cannot be executed.
 * Extends the <code>Command</code> class.
 */
public class InvalidCommand extends Command{

    private final String errorMsg;

    public InvalidCommand(String errorMsg){
        this.errorMsg = errorMsg;
    }

    public String execute(TaskList taskList, Ui ui, Storage storage) throws DukeException {
        throw new DukeException(errorMsg);
    }
}
