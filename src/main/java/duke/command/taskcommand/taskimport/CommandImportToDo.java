package duke.command.taskcommand.taskimport;

import duke.command.Command;
import duke.dukeUtility.enums.ResponseType;
import duke.TaskManager;
import duke.task.model.ToDo;

import java.util.List;


public class CommandImportToDo extends Command {
    public CommandImportToDo(ToDo toDo, TaskManager taskMgr) {
        super(ResponseType.TASK_IMPORT_TODO, List.of("create", toDo.getTaskDescription(), taskMgr.importToDo(toDo).getTaskDescription()));
    }
}
