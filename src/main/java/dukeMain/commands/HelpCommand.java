package dukeMain.commands;

import dukeMain.Storage;
import dukeMain.Ui;
import dukeMain.exceptions.DukeException;
import dukeMain.tasks.TaskList;

/**
 * Represents a Help Command being called by the users which is extended to Command class.
 * A <code>HelpCommand</code> object contains the COMMAND_WORD and MESSAGE_USAGE.
 */
public class HelpCommand extends Command{
    public static final String COMMAND_WORD = "help";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Shows program usage instructions.\n"
            + "Example: " + COMMAND_WORD;

    /**
     * Overriding the Parent class's execute.
     * Calls showError from the ui class.
     * It also calls all the other Commands'
     * MESSAGE_USAGE to print all the examples.
     *
     * @param tasks TaskList class.
     * @param ui Ui class.
     * @param storage Storage class.
     * @throws DukeException if IndexOutOfBoundsException occurs.
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        ui.showError(
                AddCommand.MESSAGE_USAGE
                        + "\n" + DeleteCommand.MESSAGE_USAGE
                        + "\n" + SaveCommand.MESSAGE_USAGE
                        + "\n" + DoneCommand.MESSAGE_USAGE
//                        + "\n" + FindCommand.MESSAGE_USAGE
                        + "\n" + ListCommand.MESSAGE_USAGE
                        + "\n" + HelpCommand.MESSAGE_USAGE
                        + "\n" + ExitCommand.MESSAGE_USAGE
        );
    }
}
