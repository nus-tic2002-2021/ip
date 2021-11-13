package dukeMain.commands;

import dukeMain.Storage;
import dukeMain.Ui;
import dukeMain.tasks.TaskList;

public class HelpCommand extends Command{
    public static final String COMMAND_WORD = "help";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Shows program usage instructions.\n"
            + "Example: " + COMMAND_WORD;

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        ui.showError(
                AddCommand.MESSAGE_USAGE
                        + "\n" + DeleteCommand.MESSAGE_USAGE
                        + "\n" + SaveCommand.MESSAGE_USAGE
                        + "\n" + DoneCommand.MESSAGE_USAGE
//                        + "\n" + FindCommand.MESSAGE_USAGE
                        + "\n" + ListCommand.MESSAGE_USAGE
//                        + "\n" + ViewCommand.MESSAGE_USAGE
//                        + "\n" + ViewAllCommand.MESSAGE_USAGE
                        + "\n" + HelpCommand.MESSAGE_USAGE
                        + "\n" + ExitCommand.MESSAGE_USAGE
        );
    }
}
