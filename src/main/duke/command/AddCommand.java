package duke.command;

import duke.storage.Storage;
import duke.tasklist.Task;
import duke.tasklist.TaskList;
import duke.ui.Ui;

public class AddCommand extends Command{
    Task task;

    public AddCommand(Task task){
        this.task = task;
    }

    public void execute(TaskList taskList, Ui ui, Storage storage) {
        taskList.addTask(task);
        ui.printAdd(task);
        String taskType = task.getType();
        switch (taskType){
            case "T":
                ui.printTodo(taskList);
                break;
            case "E":
                ui.printEvent(taskList);
                break;
            case "D":
                ui.printDeadline(taskList);
                break;
        }
    }


}
