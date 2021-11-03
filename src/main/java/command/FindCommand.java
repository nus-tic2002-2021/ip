package command;

import exception.DukeException;
import filewriter.Storage;
import task.Task;
import task.TaskList;
import ui.Ui;
import java.util.ArrayList;

/**
 * Lists all tasks containing keyword.
 */
public class FindCommand extends Command {
    String keyword;

    /**
     * Constructor for FindCommand.
     * @param keyword Searches for all tasks with keyword.
     */
    public FindCommand(String keyword) {
        super.type = FullCommand.FIND;
        this.keyword = keyword;
    }

    /**
     * Checks if it is an ExitCommand.
     * @return false.
     */
    public boolean isExit() {
        assert (!super.type.getActivityName().equals("bye"));
        return false;
    }

    /**
     * Generates a TaskList shortlist containing only tasks with the keyword.
     * Passes TaskList as an argument for ui.showMatches to display to user.
     * @param tasks TaskList containing all Tasks.
     * @param ui Instance of user interface to print feedback to user.
     * @param storage Updates data record of TaskList in storage.filepath if needed.
     * @throws DukeException when command cannot be found.
     */
    public void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        ArrayList<Task> shortlist = new ArrayList<>();
        try {
            for (Task task : tasks.getList()) {
                if (task.findWord(keyword)) {
                    shortlist.add(task);
                }
            }
            ui.showMatches(new TaskList(shortlist));
        } catch (NullPointerException | IndexOutOfBoundsException e) {
            throw new DukeException("No such keyword in the task list. Please try again. ");
        } catch (NumberFormatException e) {
            throw new DukeException("Please enter the keyword that you wish to search in the list.");
        }
    }
}