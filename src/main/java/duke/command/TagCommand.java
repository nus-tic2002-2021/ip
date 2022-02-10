package duke.command;

import duke.app.TaskList;
import duke.app.Ui;
import duke.exceptions.FileProcessError;
import duke.storage.Storage;
import duke.task.Task;

public class TagCommand extends AbstractCommand{
    @Override
    public void execute(TaskList taskList, Storage storage, Ui ui) {
        Task task = taskList.setTagForOneTask();
        try {
            storage.saveToFile(taskList.getTaskList());
            ui.printTagCommand(task.toString());
        } catch (FileProcessError e) {
            ui.showErrorMessage(e.getMessage());
        }
    }
}
