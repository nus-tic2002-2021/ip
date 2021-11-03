package command;

import exception.DukeException;
import filewriter.Storage;
import task.Task;
import task.TaskList;
import ui.Ui;

public class EditCommand extends Command {
    int index;

    /**
     * Checks if command is an ExitCommand.
     * @return false as command is an EditCommand.
     */
    public boolean isExit() {
        assert (!super.type.getActivityName().equals("bye"));
        return false;
    }

    /**
     * Constructor for EditCommand.
     * @param index of Task in TaskList to mark as done.
     */
    public EditCommand(int index) {
        this.index = index;
        super.type = FullCommand.DONE;
    }

    /**
     * Marks the (index)th task in TaskList as done.
     * @param tasks Current TaskList object used in this instance of Duke.
     * @param ui Instance of user interface to print feedback to user.
     * @param storage Updates data record of TaskList in storage.filepath if needed.
     * @throws DukeException Throws when index equals to, or when index is larger than number of Task(s) in TaskList,
     *                       or when index of Task to markAsComplete is not properly presented.
     */
    public void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        try {
            if (index == -1) {
                throw new IndexOutOfBoundsException();
            }
            Task completedTask = tasks.complete(index);
            ui.readDone(completedTask);
        } catch (NullPointerException | IndexOutOfBoundsException e) {
            throw new DukeException("The task number is not in the list. Please enter 'list' to check the task number "
                    + "that you have completed.");
        } catch (NumberFormatException e) {
            throw new DukeException("Please enter the task number that you have completed.");
        }
    }
}