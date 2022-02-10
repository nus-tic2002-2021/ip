package duke.command;

import duke.app.TaskList;
import duke.app.Ui;
import duke.exceptions.FileProcessError;
import duke.storage.Storage;

/**
 * Add a new Task to TaskList object and save the records in the text file.
 */
public class AddCommand extends AbstractCommand{

    @Override
    public void execute(TaskList taskList, Storage storage, Ui ui) {

        boolean success = taskList.addTask();
        int taskSize = taskList.getTaskList().size();
        String newTaskStr = taskList.getTaskList().get(taskSize - 1).toString();
        ui.printAddTask(success, newTaskStr);
        if (success) {
            try {
                storage.saveToFile(taskList.getTaskList());
                ui.printTotalNumber(taskSize);
            } catch (FileProcessError e) {
                ui.showErrorMessage(e.getMessage());
            }
        }
    }
}
