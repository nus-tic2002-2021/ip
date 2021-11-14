package dukeMain.commands;

import dukeMain.Storage;
import dukeMain.Ui;
import dukeMain.exceptions.DukeException;
import dukeMain.tasks.TaskList;

/**
 * Represents a Save Command being called by the users which is extended to Command class.
 * A <code>SaveCommand</code> object have 2 variable which are the Command_word and Message_usage.
 * It also includes a method 'execute' which overrides the parent class method.
 */
public class SaveCommand extends Command{
    public static final String COMMAND_WORD = "save";
    public static final String MESSAGE_USAGE = COMMAND_WORD +
            ": Save all tasks into a text file\n"
            + "Example: " + COMMAND_WORD;

    /**
     * Overriding the Parent class's execute.
     * Calls save from the storage param
     * Print FilePath to help user to locate it.
     *
     * @param tasks TaskList class.
     * @param ui Ui class.
     * @param storage Storage class.
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        // this is implemented by child class.
        storage.save(tasks);
        ui.print("Your Task have been saved in the file located in " + storage.getFilePath());
    }
}
