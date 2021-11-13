package dukeMain.commands;

import dukeMain.Storage;
import dukeMain.Ui;
import dukeMain.exceptions.DukeException;
import dukeMain.tasks.TaskList;

public class DeleteCommand extends Command{
    private static int index;

    public static final String COMMAND_WORD = "delete";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Delete a task from the List\n"
            + "Example: " + COMMAND_WORD + " 1";
    public DeleteCommand(int input){
        index = input;
    }
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException{
        // this is implemented by child class.
        try{
            tasks.deleteTask(index);
        }catch(IndexOutOfBoundsException e){
            throw new DukeException("Failed to add task !!! Here is the list of task you may add\n" + MESSAGE_USAGE);
        }
    }
}
