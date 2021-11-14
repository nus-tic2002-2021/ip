package dukeMain.commands;

import dukeMain.Storage;
import dukeMain.Ui;
import dukeMain.exceptions.DukeException;
import dukeMain.tasks.TaskList;

/**
 * Represents a Delete Command being called by the users which is extended to Command class.
 * A <code>DeleteCommand</code> object corresponds to the index (Integer) given by the users.
 *
 * e.g, <code>1</code>
 */
public class DeleteCommand extends Command{
    private static int index;

    public static final String COMMAND_WORD = "delete";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Delete a task from the List\n"
            + "Example: " + COMMAND_WORD + " 1";

    public DeleteCommand(int index){
        this.index = index;
    }

    /**
     * Overriding the Parent class's execute.
     * Calls delete from the storage param
     *
     * @param tasks TaskList class.
     * @param ui Ui class.
     * @param storage Storage class.
     * @throws DukeException if IndexOutOfBoundsException occurs.
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException{
        // this is implemented by child class.
        try{
            ui.print("The Task :");
            ui.print(tasks.deleteTask(index).toString());
            ui.print("Is removed from the list");
        }catch(IndexOutOfBoundsException e){
            throw new DukeException("Failed to delete task !!! Please follow the below example\n" + MESSAGE_USAGE);
        }
    }
}
