package commands;

import exceptions.DukeException;
import storage.Storage;
import tasks.Task;
import tasks.TaskList;
import ui.Messages;
import ui.Ui;

import java.util.ArrayList;

public class FindCommand extends Command{

    String keyword;

    public FindCommand(String keyword){
        this.keyword = keyword;
    }

    public String execute(TaskList taskList, Ui ui, Storage storage) {
        ArrayList<Task> results = taskList.searchTask(keyword);
        ui.showSearchCompleted();
        ui.printAllTasks(results);
        return Messages.SEARCH_COMPLETED_MSG + Messages.getAllTasksMsg(results);
    }
}
