package command;

import storage.Storage;
import tasklist.TaskList;
import ui.Ui;

import java.io.IOException;

/**
 * An ByeCommand make use of inherited class variable isExit
 */
public class ByeCommand extends Command{
    /**
     * The execute method changes exit status to true when called.
     * @param taskList not used here
     * @param ui to print out message on screen
     * @param storage not used here
     * @throws IOException when file not found
     */
    public void execute(TaskList taskList, Ui ui, Storage storage) {
        ui.goodBye();
        super.setExit(true);
    }
}
