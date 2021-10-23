package commands;

import storage.Storage;
import tasks.TaskList;
import ui.Ui;

import java.time.LocalDate;

public class ViewCommand extends Command{

    LocalDate date;

    public ViewCommand(LocalDate date){
        this.date = date;
    }

    public void execute(TaskList taskList, Ui ui, Storage storage) {
        ui.showScheduleFor(date);
        ui.printAllTasks(taskList.getTaskSchedule(date));
    }
}