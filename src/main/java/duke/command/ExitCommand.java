package duke.command;

import duke.Storage;
import duke.TaskList;
import duke.Ui;

public class ExitCommand extends Command{

    public ExitCommand(String req){
        this.req = req;
    }

    public void execute(TaskList tasks, Ui ui, Storage storage){
        ui.showReq(req);
        ui.showExit();
    }

    public boolean isExit(){
        return true;
    }
}
