package dukeMain.commands;

import dukeMain.Storage;
import dukeMain.Ui;
import dukeMain.exceptions.DukeException;
import dukeMain.tasks.TaskList;
/**
 * Represents a Exit Command being called by the users which is extended to Command class.
 * A <code>ExitCommand</code> object contains the COMMAND_WORD and MESSAGE_USAGE.
 *
 */
public class ExitCommand extends Command{

    public static final String COMMAND_WORD = "bye";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Displays all Task as a list with index numbers.\n"
            + "Example: " + COMMAND_WORD;

    /**
     * Overriding the Parent class's execute.
     * Set the variable 'exit' to true in parent class.
     * Thereafter print the exit message from ui Class.
     *
     * @param tasks TaskList class.
     * @param ui Ui class.
     * @param storage Storage class.
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        super.setExit(true);
        ui.showExitMessage();
    }
}
