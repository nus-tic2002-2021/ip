package duke.command;

import duke.app.TaskList;
import duke.app.Ui;
import duke.storage.Storage;

public class ExitCommand extends AbstractCommand{
    @Override
    public void execute(TaskList taskList, Storage storage, Ui ui) {
        ui.printExitMessage();
    }

    @Override
    public boolean isExit() {
        return true;
    }
}
