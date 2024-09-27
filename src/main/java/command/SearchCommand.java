package command;

import exception.DukeException;
import storage.Storage;
import tasklist.TaskList;
import ui.Ui;

import java.io.IOException;

/**
 * The SearchCommand object holds local variable keyword
 * which is used to find relevant tasks
 * and prints out the current task count
 */
public class SearchCommand extends Command{

    String keyword;

    public SearchCommand(String keyword){ this.keyword = keyword;}

    /**
     * The execute method display a list of tasks containing keywords.
     * @param taskList is the task list
     * @param ui to print out message on screen
     * @param storage not used here
     * @throws IOException when file not found
     */
    public void execute(TaskList taskList, Ui ui, Storage storage) throws DukeException {
        TaskList taskByKeyword = taskList.getTaskByKeyword(keyword, taskList);
        ui.printTaskList(taskByKeyword);
        ui.printTaskByKeyword(taskByKeyword,keyword);
    }

}
