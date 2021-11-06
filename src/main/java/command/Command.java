package command;

import storage.Storage;
import ui.UI;
import task.List;

public abstract class Command {
    protected String action;
    public Command(){

    }

    public void setAction(String action) {
        this.action = action;
    }
    public boolean isExit(){
        return false;
    }
    public abstract void execute(List tasks, Storage storage, UI ui);


}
