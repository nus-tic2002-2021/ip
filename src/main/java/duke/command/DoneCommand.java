package duke.command;

import duke.app.TaskList;
import duke.app.Ui;
import duke.exceptions.FileProcessError;
import duke.storage.Storage;

import java.io.IOException;

public class DoneCommand extends AbstractCommand{
    @Override
    public void execute(TaskList taskList, Storage storage, Ui ui) {
        try {
            String taskDone = taskList.changeTaskStatus();
            ui.printDoneMessage(taskDone);
            storage.saveToFile(taskList.getTaskList());
        } catch (NullPointerException | IndexOutOfBoundsException e) {
            ui.printIndexWrongMessage();
        } catch (FileProcessError e) {
            ui.showErrorMessage(e.getMessage());
        }
    }
}
