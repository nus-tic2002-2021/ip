package commands;

import exceptions.DukeException;
import storage.Storage;
import tasks.TaskList;
import ui.Ui;

public class InvalidCommand extends Command{

    private String errorMsg;

    public InvalidCommand(String errorMsg){
        this.errorMsg = errorMsg;
    }

    public void execute(TaskList taskList, Ui ui, Storage storage) throws DukeException {
        throw new DukeException(errorMsg);
    }
}
