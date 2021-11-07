package duke.command.taskcommand.taskimport;

import java.util.List;

import duke.TaskManager;
import duke.command.Command;
import duke.dukeutility.enums.ResponseType;
import duke.task.model.ToDo;


public class CommandImportToDo extends Command {
    /**
     * Import a todo to a task manager
     *
     * @param toDo    todo
     * @param taskMgr task manager
     */
    public CommandImportToDo(ToDo toDo, TaskManager taskMgr) {
        super(ResponseType.TASK_IMPORT_TODO,
            List.of("create", toDo.getTaskDescription(), taskMgr.importTask(toDo).getTaskDescription()));
    }

    public String getResponse() {
        return null;
    }
}
