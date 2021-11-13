package dukeMain.commands;

import dukeMain.Storage;
import dukeMain.Ui;
import dukeMain.exceptions.DukeException;
import dukeMain.tasks.TaskList;

public class DoneCommand extends Command{
    public static int index;

    public static final String COMMAND_WORD = "done";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Shows program usage instructions.\n"
            + "Example: " + COMMAND_WORD;

    public DoneCommand(int index){
        this.index = index;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        // this is implemented by child class.
        try{
            tasks.markTask(index);
        }catch(IndexOutOfBoundsException e){
            throw new DukeException("Failed to add task !!! Here is the list of task you may add\n" + MESSAGE_USAGE);
        }
    }
}
