package duke.command;

import duke.Storage;
import duke.TaskList;
import duke.Ui;
import duke.task.Task;

import java.io.IOException;

public class AddCommand extends Command{
    public Task task;

    public AddCommand(String req, Task task){
        this.task = task;
        this.req = req;
    }

    public void execute(TaskList tasks, Ui ui, Storage storage) throws IOException {
        ui.showReq(req);
        tasks.addTask(task);
        ui.showAdd(tasks, task);
        storage.store(tasks);
    }

    public boolean isExit(){
        return false;
    }
}
