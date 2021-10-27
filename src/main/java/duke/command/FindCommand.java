package duke.command;

import duke.storage.Storage;
import duke.tasklist.TaskList;
import duke.ui.Ui;

public class FindCommand extends Command {

    private String keyword;

    /**
     * Constructs FindCommand with this keyword.
     *
     * @param keyword The keyword to find the task(s).
     */
    public FindCommand(String keyword) {
        this.keyword = keyword;
    }

    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) {
        ui.printMatchingTasks(taskList.getTaskList(), keyword);
    }

}
