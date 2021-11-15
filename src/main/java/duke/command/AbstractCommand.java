package duke.command;

import duke.app.TaskList;
import duke.app.Ui;
import duke.storage.Storage;

import java.util.Map;

public abstract class AbstractCommand {
    private TaskList taskList;
    private Storage storage;
    private Ui ui;

    public abstract void execute(TaskList taskList, Storage storage, Ui ui);

    public boolean isExit() {return false;}
}
