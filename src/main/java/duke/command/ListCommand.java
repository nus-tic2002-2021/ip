package duke.command;

import duke.Storage;
import duke.TaskList;
import duke.Ui;

public class ListCommand extends Command{

    public ListCommand(String req){
        this.req = req;
    }

    public void execute(TaskList tasks, Ui ui, Storage storage){
        ui.showReq(req);
        ui.showList(tasks);
    }

    public boolean isExit(){
        return false;
    }
}
