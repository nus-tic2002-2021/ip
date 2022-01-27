package dukeMain.commands;

import dukeMain.Storage;
import dukeMain.Ui;
import dukeMain.exceptions.DukeException;
import dukeMain.tasks.TaskList;

/**
 * Represents an Add Command being called by the users which is extended to Command class.
 * A <code>AddCommand</code> object corresponds to
 * the 3 different types of Tasks to be added to the task.
 * It can be represented by a String 'Command' and String Array 'arguments'
 * e.g, <code>'event', {'test',event','23/12/2021'}</code>
 */
public class AddCommand extends Command{
    public static final String COMMAND_WORD_1 = "todo";
    public static final String COMMAND_WORD_2 = "deadline";
    public static final String COMMAND_WORD_3 = "event";

    public static final String COMMAND_WORD_SHORT_1 = "T";
    public static final String COMMAND_WORD_SHORT_2 = "D";
    public static final String COMMAND_WORD_SHORT_3 = "E";
    private static String[] arguments;

    public static final String MESSAGE_USAGE = COMMAND_WORD_1
            + ": Add a To Do Task into Task List\n"
            + "Example: " + COMMAND_WORD_1+" Description" + "\n\n" +
            COMMAND_WORD_2
            + ": Add a Task with Deadline into Task List\n"
            + "Example: " + COMMAND_WORD_2+" Description / DD.MM.YYYY" + "\n" +
            COMMAND_WORD_3
            + ": Add a Event into Task List\n\n"
            + "Example: " + COMMAND_WORD_3+" Description / DD.MM.YYYY\n"
            + "Note Please do not use / to differential DD/MM/YYYY";

    public AddCommand(String command, String[] arguments){
        super(command);
        this.arguments = arguments;
    }

    /**
     * Overriding the Parent class's execute.
     * Calls addTask from the taskList param
     *
     * @param tasks TaskList class.
     * @param ui Ui class.
     * @param storage Storage class.
     *
     * @throws DukeException If task is not being added due to certain reasons.
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException{
        // this is implemented by child class.

        tasks.addTask(getCommand(),arguments,false);
        ui.print("Your Task : ");
        ui.print(tasks.getTask(tasks.getTaskList().size()-1).toString());
        ui.print("Is Added to the list. Now you have "+tasks.getTaskList().size() +" Tasks in the list");
    }
}
