package dukeMain.commands;

import dukeMain.Storage;
import dukeMain.Ui;
import dukeMain.tasks.TaskList;
/**
 * Represents a List Command being called by the users which is extended to Command class.
 * A <code>ListCommand</code> object contains the COMMAND_WORD and MESSAGE_USAGE.
 */
public class ListCommand extends Command{
    public static final String COMMAND_WORD = "list";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Displays all Task as a list with index numbers.\n"
            + "Example: " + COMMAND_WORD;

    /**
     * Overriding the Parent class's execute.
     * Calls printList from the ui class
     * and getTaskList from tasks.
     *
     * @param tasks TaskList class.
     * @param ui Ui class.
     * @param storage Storage class.
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        // this is implemented by child class.
       ui.printList(tasks.getTaskList());
    }

}
