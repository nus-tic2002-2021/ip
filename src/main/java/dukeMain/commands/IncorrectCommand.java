package dukeMain.commands;

import dukeMain.Storage;
import dukeMain.Ui;
import dukeMain.exceptions.DukeException;
import dukeMain.tasks.TaskList;

/**
 * Represents an Incorrect Command being called by the users which is extended to Command class.
 * A <code>IncorrectCommand</code> object contains the COMMAND_WORD and MESSAGE_USAGE.
 *e.g <code>"This is an incorrect command. Please try again."</code>
 */
public class IncorrectCommand extends Command {
    private String errormessage;

    public IncorrectCommand(String errormessage){
        this.errormessage = errormessage;
    }

    /**
     * Overriding the Parent class's execute.
     * Calls showError from the ui class.
     *
     *
     * @param tasks TaskList class.
     * @param ui Ui class.
     * @param storage Storage class.
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        ui.showError(errormessage);
    }
}
