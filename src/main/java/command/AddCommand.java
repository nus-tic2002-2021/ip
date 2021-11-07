package command;

import storage.Storage;
import ui.UI;
import task.List;
import error.*;

public class AddCommand extends Command {
    protected String add;

    public AddCommand(CommandType action, String add) {
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

    }
    /**
     * task function to determine the type of task to add.
     * Action is an enumeration of TODO, DEADLINE and EVENT,
     * where the String action only allows todo, deadline and event.
     * Converts action into an Enumeration and determine the type of task to
     * be added. inputMsg determines what is the full command description sent
     * when adding task.
     *
     * @param action   the type of task to be added
     * @param inputMsg the message be added in taskArrayList
     */
    public void addTask(CommandType action, String inputMsg, List tasks) throws DukeException {
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
