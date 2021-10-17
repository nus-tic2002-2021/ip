package commands;

import exceptions.DukeException;
import storage.Storage;
import tasks.TaskList;
import ui.Ui;

import java.util.Optional;

/**
 * <code>InvalidCommand</code> is used to capture invalid input command that cannot be executed.
 * Extends the <code>Command</code> class.
 */
public class InvalidCommand extends Command{

    private String errorMsg;

    public InvalidCommand(String errorMsg){
        this.errorMsg = errorMsg;
    }

    public void execute(TaskList taskList, Ui ui, Storage storage) throws DukeException {
        throw new DukeException(errorMsg);
    }
}
