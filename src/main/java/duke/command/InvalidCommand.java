package duke.command;

import duke.tasklist.*;
import duke.storage.*;
import duke.ui.*;
import duke.exception.*;

public class InvalidCommand extends Command {

    private String message;

    public InvalidCommand(String message){
        this.message = message;
    }

    public void execute(TaskList taskList, Ui ui, Storage storage) throws DukeException {
        throw new DukeException(message);
    }

}
