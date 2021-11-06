package command;

import storage.Storage;
import ui.UI;
import task.List;
import error.*;

public class ModifyCommand extends Command {
    protected String modify;
    public ModifyCommand(String action, String modify){
        setAction(action);
        setModify(modify);
    }
    public void setModify(String modify) {
        this.modify = modify;
    }
    public void execute(List tasks, Storage storage, UI ui){
        try {
            tasks.taskDone(modify);
        } catch(NotFoundException e) {
            ui.printNotFound();
        } catch (NumberFormatException e){
            ui.printInvalidEntry();
        }
    }
}