package duke.command.taskcommand.taskadd;

import java.util.List;
import duke.TaskManager;
import duke.command.Command;
import duke.dukeUtility.enums.ResponseType;


public class CommandAddNewToDo extends Command {

    /**
     * Task manager will add a new todo to its collection with provided parameters
     *
     * @param tm   task manager
     * @param desc of todo
     */

    public CommandAddNewToDo(TaskManager tm, String desc) {
        super(ResponseType.TASK_CREATE_TODO,
            List.of("create", desc, tm.addNewToDo(desc).getTaskDescription()));
    }
}
