package dukeMain.commands;

import dukeMain.Storage;
import dukeMain.Ui;
import dukeMain.exceptions.DukeException;
import dukeMain.tasks.TaskList;

public class AddCommand extends Command{
    public static final String COMMAND_WORD_1 = "todo";
    public static final String COMMAND_WORD_2 = "deadline";
    public static final String COMMAND_WORD_3 = "event";
    private static String[] arguments;

    public static final String MESSAGE_USAGE = COMMAND_WORD_1
            + ": Add a To Do Task into Task List\n"
            + "Example: " + COMMAND_WORD_1+" Description" + "\n\n" +
            COMMAND_WORD_2
            + ": Add a Task with Deadline into Task List\n"
            + "Example: " + COMMAND_WORD_2+" Description / DD.MM.YYYY" + "\n" +
            COMMAND_WORD_3
            + ": Add a Event into Task List\n\n"
            + "Example: " + COMMAND_WORD_3+" Description / DD.MM.YYYY";

    public AddCommand(String command, String[] arguments){
        super(command);
        this.arguments = arguments;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException{
        // this is implemented by child class.
        tasks.addTask(getUserCommand(),arguments,false);
    }
}
