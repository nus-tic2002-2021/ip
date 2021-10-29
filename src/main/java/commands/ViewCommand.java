package commands;

import storage.Storage;
import tasks.TaskList;
import ui.Messages;
import ui.Ui;

import java.time.LocalDate;

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