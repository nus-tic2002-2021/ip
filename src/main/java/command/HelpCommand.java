package command;

import exception.DukeException;
import storage.Storage;
import tasklist.TaskList;
import ui.Ui;

import java.io.IOException;

/**
 * An HelpCommand object displays the help message on screen
 */
public class HelpCommand extends Command{
    /**
     * The execute method displays the help message on screen
     * @param taskList not used here
     * @param ui to print out message on screen
     * @param storage not used here
     * @throws IOException when file not found
     */
    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) throws DukeException, IOException {
        ui.howToUse();
    }
}
