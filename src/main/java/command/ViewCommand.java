package command;

import storage.Storage;
import tasklist.TaskList;
import ui.Ui;

import java.io.IOException;
import java.time.LocalDate;

/**
 * An ViewCommand object holds local variable date
 * which is used to find relevant tasks
 */
public class ViewCommand extends Command {
    LocalDate date;

    public ViewCommand(LocalDate date){
        this.date = date;
    }

    /**
     * The execute method display a list of tasks on date given.
     * @param taskList is the task list
     * @param ui to print out message on screen
     * @param storage not used here
     * @throws IOException when file not found
     */
    public void execute(TaskList taskList, Ui ui, Storage storage) {
        TaskList taskByDate = taskList.getTaskByDate(date, taskList);
        ui.printTaskList(taskByDate);
        ui.printTaskOfDate(taskByDate, date);
    }
}
