package duke.command;

import duke.Storage;
import duke.TaskList;
import duke.Ui;
import duke.task.Task;

import java.io.IOException;

public class DoneCommand extends Command{

    public DoneCommand(String req, int idx){
        this.idx = idx;
        this.req = req;
    }

    public void execute(TaskList tasks, Ui ui, Storage storage) throws IOException {
        ui.showReq(req);
        Task t = tasks.doneTask(idx);
        ui.showDone(t);
        storage.store(tasks);
    }

    public boolean isExit(){
        return false;
    }
}
