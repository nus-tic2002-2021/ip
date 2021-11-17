package commands;

import storage.Storage;
import tasks.TaskList;
import ui.Messages;
import ui.Ui;

import java.time.LocalDate;

/**
 * <code>ViewCommand</code> is used to list all the tasks scheduled on the input date.
 * Extends the <code>Command</code> class.
 */
public class ViewCommand extends Command{

    LocalDate date;

    public ViewCommand(LocalDate date){
        this.date = date;
    }

    public String execute(TaskList taskList, Ui ui, Storage storage) {
        ui.showScheduleFor(date);
        ui.printAllTasks(taskList.getTaskSchedule(date));
        return Messages.getScheduledMsg(date) + Messages.getAllTasksMsg(taskList.getTaskSchedule(date));
    }
}