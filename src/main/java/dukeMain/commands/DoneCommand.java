package dukeMain.commands;

import dukeMain.Storage;
import dukeMain.Ui;
import dukeMain.exceptions.DukeException;
import dukeMain.tasks.TaskList;

/**
 * Represents a Done Command being called by the users which is extended to Command class.
 * A <code>DoneCommand</code> object corresponds to the index (Integer) given by the users.
 *
 * e.g, <code>1</code>
 */
public class DoneCommand extends Command{
    public static int index;

    public static final String COMMAND_WORD = "done";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Shows program usage instructions.\n"
            + "Example: " + COMMAND_WORD;

    public DoneCommand(int index){
        this.index = index;
    }

    /**
     * Overriding the Parent class's execute.
     * Calls markTask from the tasks param
     *
     * @param tasks TaskList class.
     * @param ui Ui class.
     * @param storage Storage class.
     * @throws DukeException if IndexOutOfBoundsException occurs.
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        // this is implemented by child class.
        try{
            tasks.markTask(index);
            ui.print("Your Task :");
            ui.print(tasks.getTask(index-1).toString());
            ui.print("Is marked as completed");
        }catch(IndexOutOfBoundsException e){
            throw new DukeException("Failed to add task !!! Here is the list of task you may add\n" + MESSAGE_USAGE);
        }
    }
}
