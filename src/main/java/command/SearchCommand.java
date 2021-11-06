package command;

import exception.DukeException;
import storage.Storage;
import tasklist.TaskList;
import ui.Ui;

public class SearchCommand extends Command{

    String keyword;

    public SearchCommand(String keyword){ this.keyword = keyword;}

    public void execute(TaskList taskList, Ui ui, Storage storage) throws DukeException {
        TaskList taskByKeyword = taskList.getTaskByKeyword(keyword, taskList);
        ui.printTaskList(taskByKeyword);
        ui.printTaskByKeyword(taskByKeyword,keyword);
    }

}
