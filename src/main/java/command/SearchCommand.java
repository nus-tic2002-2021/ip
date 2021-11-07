package command;

import exception.DukeException;
import storage.Storage;
import tasklist.TaskList;
import ui.Ui;

/**
 * The SearchCommand object clears all task in list
 * and prints out the current task count
 * and writes the change to tasks.txt file
 */
public class SearchCommand extends Command{

    String keyword;

    public SearchCommand(String keyword){ this.keyword = keyword;}

    public void execute(TaskList taskList, Ui ui, Storage storage) throws DukeException {
        TaskList taskByKeyword = taskList.getTaskByKeyword(keyword, taskList);
        ui.printTaskList(taskByKeyword);
        ui.printTaskByKeyword(taskByKeyword,keyword);
    }

}
