package duke.command;

import duke.Storage;
import duke.TaskList;
import duke.Ui;
import duke.task.Task;

import java.io.IOException;

public class DeleteCommand extends Command{
    public int idx;

    public DeleteCommand(String req, int idx){
        this.idx = idx;
        this.req = req;
    }

    public void execute(TaskList tasks, Ui ui, Storage storage) throws IOException {
        ui.showReq(req);
        Task t = tasks.deleteTask(idx);
        ui.showDelete(tasks, t);
        storage.store(tasks);
    }

    public boolean isExit(){
        return false;
    }
}
