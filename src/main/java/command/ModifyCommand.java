package command;

import storage.Storage;
import ui.UI;
import task.List;
import error.*;

public class ModifyCommand extends Command {
    protected String modify;

    public ModifyCommand(CommandType action, String modify) {
        setAction(action);
        setModify(modify);
    }

    public void setModify(String modify) {
        this.modify = modify;
    }

    public void execute(List tasks, Storage storage, UI ui) {
        try {
            switch (action) {
            case DONE:
                tasks.taskDone(modify);
                break;
            case DELETE:
                tasks.taskDelete(modify);
                break;
            default:
                assert false : action;
            }

        } catch (NotFoundException e) {
            ui.printNotFound();
        } catch (NumberFormatException e) {
            ui.printInvalidEntry();
        }
    }
}