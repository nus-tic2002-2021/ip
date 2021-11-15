package duke.command;

import duke.app.TaskList;
import duke.app.Ui;
import duke.exceptions.FileProcessError;
import duke.storage.Storage;

public class AddCommand extends AbstractCommand{

    @Override
    public void execute(TaskList taskList, Storage storage, Ui ui) {

        boolean success = taskList.addTask();
        int taskSize = taskList.getTaskList().size();
        String newTaskStr = taskList.getTaskList().get(taskSize - 1).toString();
        ui.printAddTask(success, newTaskStr);
        ui.printTotalNumber(taskSize);
        if (success) {
            try {
                storage.saveToFile(taskList.getTaskList());
            } catch (FileProcessError e) {
                ui.showErrorMessage(e.getMessage());
            }
        }
    }
}
