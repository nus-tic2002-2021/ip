package duke.command;

import duke.Storage;
import duke.TaskList;
import duke.Ui;

import java.io.IOException;

public class FindCommand extends Command{
    public String search;

    public FindCommand(String req, String search){
        this.req = req;
        this.search = search;
    }

    public void execute(TaskList tasks, Ui ui, Storage storage) throws IOException {
        ui.showReq(req);
        TaskList tl = tasks.find(search);
        ui.showFind(tl);
    }

    public boolean isExit(){
        return false;
    }
}
