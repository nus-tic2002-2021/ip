package command;

import exception.DukeException;
import filewriter.Storage;
import task.TaskList;
import ui.Ui;

/**
 * Displays the full list of recorded tasks to user.
 */
public class ListCommand extends Command {
    int index;

    /**
     * Constructor of ListCommand.
     */
    public ListCommand() {
        super.type = FullCommand.LIST;
    }

    /**
     * Checks if command is an ExitCommand.
     * @return ConstructorCommand is not ExitCommand.
     */
    public boolean isExit() {
        assert (!super.type.getActivityName().equals("bye"));
        return false;
    }

    /**
     * Passes TaskList as an argument for ui.readList to display to user.
     * @param tasks Current TaskList object used in this instance of Duke.
     * @param ui Instance of user interface to print feedback to user.
     * @param storage Updates data record of TaskList in storage.filepath if needed.
     * @throws DukeException if the number is not in the list or no number was indicated.
     */
    public void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        try {
            if (index == -1) {
                throw new IndexOutOfBoundsException();
            }
            ui.readList(tasks);
        } catch (NullPointerException | IndexOutOfBoundsException e) {
            throw new DukeException("You have 0 task in the list.");
        } catch (NumberFormatException e) {
            throw new DukeException("Please provide commands in the format: (command type) (task details). "
                    + "For more information, please type 'instruction'.");
        }
    }
}