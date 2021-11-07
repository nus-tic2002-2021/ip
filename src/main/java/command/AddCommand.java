package command;

import storage.Storage;
import task.Action;
import ui.UI;
import task.List;
import error.*;

public class AddCommand extends  Command{
    protected String add;
    public AddCommand(CommandType action, String add){
        setAction(action);
        setAdd(add);
    }
    public void setAdd(String add) {
        this.add = add;
    }

    public void execute(List tasks, Storage storage, UI ui) {
        try {
            addTask(action, add, tasks);
        } catch (DukeException e) {
            ui.showError(e.getMessage());
        }

    }    public void addTask(CommandType action, String inputMsg, List tasks) throws DukeException {
        switch (action) {
        case TODO:
            tasks.addTodo(inputMsg);
            break;
        case DEADLINE:
            tasks.addDeadline(inputMsg);
            break;
        case EVENT:
            tasks.addEvent(inputMsg);
            break;
        default:
            assert false : action;
        }
    }
}
