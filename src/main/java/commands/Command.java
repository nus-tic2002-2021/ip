package commands;

import exceptions.DukeException;
import storage.Storage;
import tasks.TaskList;
import ui.Ui;

import java.io.IOException;

/**
 * This <code>Command</code> class is an abstract class to be extended by other command classes.
 */
public abstract class Command {

    protected boolean isExit;

    public Command(){}

    /**
     * Executes a command and returns the success message.
     * Needs to be implemented in other command classes (abstract).
     *
     * @param taskList TaskList object where all available tasks are stored.
     * @param ui Ui object to print statements for user interaction.
     * @param storage Storage object to save the tasks into specific file.
     *
     * @throws DukeException If command execution failed.
     * @throws IOException If file writing/ saving failed.
     */
    public abstract String execute(TaskList taskList, Ui ui, Storage storage) throws DukeException, IOException;

    /**
     * Sets program exit flag.
     *
     * @param exit Exit flag (true or false).
     */
    public void setExit(boolean exit){
        isExit = exit;
    }

    /** Returns the exit flag */
    public boolean isExit(){
        return isExit;
    }
}
