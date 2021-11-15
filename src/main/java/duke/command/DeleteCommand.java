package duke.command;

import duke.app.TaskList;
import duke.app.Ui;
import duke.exceptions.FileProcessError;
import duke.storage.Storage;
import java.io.IOException;

public class DeleteCommand extends AbstractCommand {
    @Override
    public void execute(TaskList taskList, Storage storage, Ui ui) {
        try {
            String removedTask = taskList.deleteTask();
            ui.printDeleteTask(removedTask);
            ui.printTotalNumber(taskList.getTaskList().size());
            storage.saveToFile(taskList.getTaskList());
        } catch (IndexOutOfBoundsException e) {
            ui.printIndexWrongMessage();
        } catch (FileProcessError e) {
            ui.showErrorMessage(e.getMessage());
        }
    }
}
