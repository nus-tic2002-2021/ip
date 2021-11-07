package command;

import exception.DukeException;
import storage.Storage;
import tasklist.TaskList;
import ui.Ui;

import java.io.IOException;

/**
 * Any unrecognized command will be handled by this class
 */
public class InvalidCommand extends Command{
    private final String errorMsg;

    public InvalidCommand(String errorMsg){
        this.errorMsg = errorMsg;
    }

    /**
     * The execute method displays the error message on screen
     * @param taskList is the task list
     * @param ui to print out message on screen
     * @param storage not used here
     * @throws IOException when file not found
     */
    public void execute(TaskList taskList, Ui ui, Storage storage) throws DukeException {
        throw new DukeException(errorMsg);
    }
}
