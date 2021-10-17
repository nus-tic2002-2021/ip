package commands;

import exceptions.DukeException;
import storage.Storage;
import tasks.Task;
import tasks.TaskList;
import ui.Ui;

import java.io.IOException;
import java.util.ArrayList;

public class FindCommand extends Command{

    String keyword;

    public FindCommand(String keyword){
        this.keyword = keyword;
    }

    public void execute(TaskList taskList, Ui ui, Storage storage) throws DukeException, IOException {
        ArrayList<Task> results = taskList.searchTask(keyword);
        ui.showSearchCompleted();
        ui.printAllTasks(results);
    }
}
