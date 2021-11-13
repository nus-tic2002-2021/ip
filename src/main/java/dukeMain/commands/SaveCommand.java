package dukeMain.commands;

import dukeMain.Storage;
import dukeMain.Ui;
import dukeMain.tasks.TaskList;

public class SaveCommand extends Command{
    public static final String COMMAND_WORD = "save";
    public static final String MESSAGE_USAGE = COMMAND_WORD +
            ": Save all tasks into a text file\n"
            + "Example: " + COMMAND_WORD;
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        // this is implemented by child class.
        storage.save(tasks);
//        ui.printList(tasks.getTaskList());
    }
}
