package Duke.parser;

import Duke.storage.Storage;
import Duke.task.TaskList;
import Duke.task.UnscheduledTaskList;
import Duke.ui.UI;

public class Command {

    private CMD cmd;

    private boolean isExit;

    public void execute(TaskList taskList, UI ui, Storage storage) {
        this.isExit = cmd.execute(taskList, ui, storage);
    }


    public boolean isExit() {

        return this.isExit;
    }

    public CMD getCmd() {
        return cmd;
    }

    public void setCmd(CMD cmd) {
        this.cmd = cmd;
    }
}