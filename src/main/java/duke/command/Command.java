package duke.command;

import duke.Storage;
import duke.TaskList;
import duke.Ui;

import java.io.IOException;

public abstract class Command {
    public String req;

    public abstract void execute(TaskList tasks, Ui ui, Storage storage) throws IOException;

    public abstract boolean isExit();
}
