package dukeMain.commands;

import dukeMain.Storage;
import dukeMain.Ui;
import dukeMain.tasks.TaskList;

public class ExitCommand extends Command{

    public static final String COMMAND_WORD = "bye";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Displays all Task as a list with index numbers.\n"
            + "Example: " + COMMAND_WORD;

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        super.setExit(true);
        ui.showExitMessage();
    }
}
