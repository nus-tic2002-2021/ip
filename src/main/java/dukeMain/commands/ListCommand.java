package dukeMain.commands;

import dukeMain.Storage;
import dukeMain.Ui;
import dukeMain.tasks.TaskList;

public class ListCommand extends Command{
    public static final String COMMAND_WORD = "list";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Displays all Task as a list with index numbers.\n"
            + "Example: " + COMMAND_WORD;

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        // this is implemented by child class.
       ui.printList(tasks.getTaskList());
    }

}
