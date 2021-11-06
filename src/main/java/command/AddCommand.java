package command;

import storage.Storage;
import ui.UI;
import task.List;
import error.*;

public class AddCommand extends  Command{
    protected String add;
    public AddCommand(String action, String add){
        setAction(action);
        setAdd(add);
    }
    public void setAdd(String add) {
        this.add = add;
    }

    public void execute(List tasks, Storage storage, UI ui) {
        try {
            tasks.addTask(action, add);
        } catch (DukeException e) {
            ui.showError(e.getMessage());
        }

    }
}
