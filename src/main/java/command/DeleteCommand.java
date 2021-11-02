package command;

import duke.Storage;
import duke.UI;
import task.List;
import error.*;

public class DeleteCommand extends Command{
    protected String delete;
    public DeleteCommand(String action, String delete){
        setAction(action);
        setDelete(delete);
    }
    public void setDelete(String delete) {
        this.delete = delete;
    }
    public void execute(List tasks, Storage storage, UI ui){
        try {
            tasks.taskDelete(delete);
        } catch(NotFoundException e) {
            ui.printNotFound();
        } catch (NumberFormatException e){
            ui.printInvalidEntry();
        }
    }
}